package com.auth0.jerseyrest.resources;

import com.auth0.jerseyrest.model.Task;
import com.auth0.jerseyrest.request.TaskRequest;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

@Path("/tasks")
@Singleton
public class TaskResource {

    private Map<UUID, Task> tasks = new LinkedHashMap<>();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String createTask(TaskRequest request) {
        UUID taskId = UUID.randomUUID();
        tasks.put(taskId, new Task(taskId, request.getDescription()));
        return taskId.toString();
    }

    @PUT
    @Path("/{taskId}")
    public Response updateTask(@PathParam("taskId") UUID taskId, TaskRequest request) {
        if (!tasks.containsKey(taskId)) return Response.status(Response.Status.NOT_FOUND).build(); // return 404

        Task task = tasks.get(taskId);
        task.setDescription(request.getDescription());

        return Response.noContent().build(); // return 204
    }

    @DELETE
    @Path("/{taskId}")
    public Response deleteTask(@PathParam("taskId") UUID taskId) {
        tasks.remove(taskId);
        return Response.noContent().build();
    }
}
