package io.github.bhuwanupadhyay.casestudy.sales.infrastructure.configurations;

import io.github.bhuwanupadhyay.casestudy.sales.domain.model.repositories.Orders;
import io.github.bhuwanupadhyay.casestudy.sales.domain.services.CancelOrderCommandService;
import io.github.bhuwanupadhyay.casestudy.sales.domain.services.ModifyOrderCommandService;
import io.github.bhuwanupadhyay.casestudy.sales.domain.services.PlaceOrderCommandService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandServiceConfiguration {

	@Bean
	public PlaceOrderCommandService placeOrderCommandService(Orders orders) {
		return new PlaceOrderCommandService(orders);
	}

	@Bean
	public ModifyOrderCommandService modifyOrderCommandService(Orders orders) {
		return new ModifyOrderCommandService(orders);
	}

	@Bean
	public CancelOrderCommandService cancelOrderCommandService(Orders orders) {
		return new CancelOrderCommandService(orders);
	}

}
