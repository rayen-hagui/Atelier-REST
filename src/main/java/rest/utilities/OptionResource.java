package rest.utilities;

import entities.Option;
import metiers.OptionBusiness;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;
import java.util.ArrayList;

@Path("/options")
public class OptionResource {

    public static OptionBusiness optionBusiness = new OptionBusiness();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response ajouterOption(Option option) {

        boolean ajout = optionBusiness.addOption(option);

        if (ajout) {
            return Response.status(200).build();
        }

        return Response.status(404).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOptions(@QueryParam("domaine") String domaine) {

        List<Option> l = optionBusiness.getListeOptions();

        if (domaine == null) {
            return Response.status(200).entity(l).build();
        }

        List<Option> resultat = new ArrayList<>();

        for (Option option : l) {
            if (domaine.equalsIgnoreCase(option.getDomaine())) {
                resultat.add(option);
            }
        }

        return Response.status(200).entity(resultat).build();
    }

    @DELETE
    @Path("/{id}")
    public Response supprimerOption(@PathParam("id") int id) {

        boolean suppression = optionBusiness.deleteOption(id);

        if (suppression) {
            return Response.status(204).build();
        }

        return Response.status(404).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modifierOption(@PathParam("id") int id, Option option) {

        boolean modification = optionBusiness.updateOption(id, option);

        if (modification) {
            return Response.status(200).build();
        }

        return Response.status(404).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOptionParId(@PathParam("id") int id) {

        List<Option> options = optionBusiness.getListeOptions();

        for (Option option : options) {
            if (option.getCodeOption() == id) {
                return Response.status(200).entity(option).build();
            }
        }

        return Response.status(404).build();
    }
}