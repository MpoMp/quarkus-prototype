package dev.reservationsrvc.infra.inventory;

import io.smallrye.graphql.client.typesafe.api.GraphQLClientApi;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.graphql.Query;
import org.jspecify.annotations.NullMarked;

import java.util.List;

@NullMarked
@GraphQLClientApi(configKey = "inventory-gql") //config key allows for multiple GQL clients to be used simultaneously
public interface GraphQLInventoryClient extends InventoryClient{

    @Override
    @Query("cars")
    List<Car> allCars() ;
}
