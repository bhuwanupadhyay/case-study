package io.github.bhuwanupadhyay.casestudy.fulfillment.infrastructure.configurations;

import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.model.repositories.Shippings;
import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.services.CancelShippingCommandService;
import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.services.ModifyShippingCommandService;
import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.services.PrepareShippingCommandService;
import io.github.bhuwanupadhyay.casestudy.fulfillment.domain.services.ShipOrderCommandService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandServiceConfiguration {

  @Bean
  public PrepareShippingCommandService prepareShippingCommandService(Shippings shippings) {
    return new PrepareShippingCommandService(shippings);
  }

  @Bean
  public ShipOrderCommandService shipOrderCommandService(Shippings billings) {
    return new ShipOrderCommandService(billings);
  }

  @Bean
  public CancelShippingCommandService cancelShippingCommandService(Shippings billings) {
    return new CancelShippingCommandService(billings);
  }

  @Bean
  public ModifyShippingCommandService modifyShippingCommandService(Shippings billings,
      PrepareShippingCommandService prepareShippingCommandService) {
    return new ModifyShippingCommandService(billings, prepareShippingCommandService);
  }
}
