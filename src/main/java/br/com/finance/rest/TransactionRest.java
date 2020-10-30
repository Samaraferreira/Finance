package br.com.finance.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import br.com.finance.dto.TransactionDto;
import br.com.finance.service.TransactionService;

@Path("transaction")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed({"USER","ADMIN"})
public class TransactionRest {
	
	@Inject
	TransactionService service;
	
	@GET
	@Path("")
	@Operation(summary = "Listar transações",
	description = "Retorna uma lista de Transaction.class")
	@APIResponse(responseCode = "200",
	description = "lista de transações",
	content = {
			@Content(mediaType =  "application/json",
			schema = @Schema(implementation = TransactionDto.class, type = SchemaType.ARRAY))
			}
	)
	public Response list() {
		
		return Response
				.status(Response.Status.OK)
				.entity(service.list())
				.build();
	}
	
	@POST
	@Path("")
	@Operation(summary = "Incluir uma transação",
	description = "Incluir uma transação")
	@APIResponse(responseCode = "201",
	description = "transação",
	content = {
			@Content(mediaType =  "application/json",
			schema = @Schema(implementation = TransactionDto.class))
			}
	)
	public Response create(TransactionDto expense, @Context SecurityContext securityContext) {
		
		service.create(expense);
		
		return Response
				.status(Response.Status.CREATED)
				.build();
	}
}

