help:
	@echo ""
	@echo "usage: make COMMAND"
	@echo ""
	@echo "Commands:"
	@echo "  init                Create libs & containers"
	@echo "  up                  Up containers"
	@echo "  down                Stop containers"
	@echo "  clean               Stop & remove project containers"
	@echo "  prune               Remove volumes, layers & stopped containers"
	@echo "  rmi                 Remove containers"

init:
	-@mkdir -p ./docker/data/postgres
	@docker-compose -f ./docker-compose.yaml --env-file ./docker/.env up -d --build

up:
	@docker-compose -f ./docker-compose.yaml --env-file ./docker/.env up -d

down:
	@docker-compose -f ./docker-compose.yaml --env-file ./docker/.env stop

clean:
	-@docker rm $$(docker stop hillel-postgres)

prune:
	@docker system prune -f
	@docker volume prune -f

rmi:
	@docker rmi $$(docker images -aq)

.PHONY: clean init help