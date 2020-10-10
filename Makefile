infra:
	sudo sysctl -w vm.max_map_count=262144 && \
	docker-compose rm -f -s -v && docker-compose up -d