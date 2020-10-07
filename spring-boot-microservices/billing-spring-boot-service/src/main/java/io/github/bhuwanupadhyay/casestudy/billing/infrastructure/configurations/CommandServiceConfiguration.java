package io.github.bhuwanupadhyay.casestudy.billing.infrastructure.configurations;

import io.github.bhuwanupadhyay.casestudy.billing.domain.model.repositories.Billings;
import io.github.bhuwanupadhyay.casestudy.billing.domain.services.ChargeOrderCommandService;
import io.github.bhuwanupadhyay.casestudy.billing.domain.services.InventoryService;
import io.github.bhuwanupadhyay.casestudy.billing.domain.services.ModifyChargeCommandService;
import io.github.bhuwanupadhyay.casestudy.billing.domain.services.RefundOrderCommandService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandServiceConfiguration {

  @Bean
  public ChargeOrderCommandService chargeOrderCommandService(Billings billings, InventoryService inventoryService) {
    return new ChargeOrderCommandService(billings, inventoryService);
  }

  @Bean
  public ModifyChargeCommandService modifyChargeCommandService(Billings billings) {
    return new ModifyChargeCommandService(billings);
  }

  @Bean
  public RefundOrderCommandService refundOrderCommandService(Billings billings) {
    return new RefundOrderCommandService(billings);
  }
}
