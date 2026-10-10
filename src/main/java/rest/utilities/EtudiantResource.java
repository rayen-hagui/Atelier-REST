package rest.utilities;

import entities.Etudiant;
import entities.Option;
import metiers.EtudiantBusiness;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

@Path("/etudiants")
public class EtudiantResource {

    public static EtudiantBusiness etudiantBusiness = new EtudiantBusiness();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response ajouterEtudiant(Etudiant etudiant) {

        if (etudiant == null || etudiant.getOption() == null) {
            return Response.status(404).build();
        }

        boolean ajout = etudiantBusiness.addEtudiant(etudiant);

        if (ajout) {
            return Response.status(200).build();
        }

        return Response.status(404).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEtudiants() {

        List<Etudiant> etudiants = etudiantBusiness.getAllEtudiants();

        return Response.status(200).entity(etudiants).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEtudiantParId(@PathParam("id") String id) {

        Etudiant etudiant = etudiantBusiness.getEtudiantByIdentifiant(id);

        if (etudiant == null) {
            return Response.status(404).build();
        }

        return Response.status(200).entity(etudiant).build();
    }

    @DELETE
    @Path("/{id}")
    public Response supprimerEtudiant(@PathParam("id") String id) {

        boolean suppression = etudiantBusiness.deleteEtudiant(id);

        if (suppression) {
            return Response.status(204).build();
        }

        return Response.status(404).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modifierEtudiant(@PathParam("id") String id,
                                     Etudiant etudiant) {

        if (etudiant == null) {
            return Response.status(400).build();
        }

        etudiant.setIdentifiant(id);

        boolean modification = etudiantBusiness.updateEtudiant(id, etudiant);

        if (modification) {
            return Response.status(200).build();
        }

        return Response.status(404).build();
    }

    @GET
    @Path("/option")
    @Produces(MediaType.APPLICATION_XML)
    public Response getEtudiantsParOption(
            @QueryParam("codeOption") int codeOption) {

        Option option = OptionResource.optionBusiness.getOptionByCode(codeOption);

        if (option == null) {
            return Response.status(404).build();
        }

        List<Etudiant> liste = etudiantBusiness.getEtudiantsByOption(option);

        return Response.status(200).entity(new Etudiants(liste)).build();
    }
}