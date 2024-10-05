# Projeto API Rest

## Detalhamento do Time

Este projeto foi desenvolvido individualmente por **Ana Clara Barros**, que implementou todos os endpoints, services, repositories, models (anotados com @Entity) e DTOs da aplicação.

## Detalhamento dos endpoints:

### *Film*:

CRUD completo

    - GET /films
    - GET /films/{id}
    - POST /films
    - PUT /films/{id}
    - DELETE /films/{id}

### *Director*:

CRUD completo

    - GET /directors
    - GET /directors/{id}
    - POST /directors
    - PUT /directos/{id}
    - DELETE /directors/{id}

### *Genre*:

CRUD completo

    - GET /genres
    - GET /genres/{id}
    - POST /genres
    - PUT /genres/{id}
    - DELETE /genres/{id}

### *Actor*:

CRUD completo

    - GET /actors
    - GET /actors/{id}
    - POST /actors
    - PUT /actors/{id}
    - DELETE /actors/{id}

### *Producer*:

CRUD completo

    - GET /producers
    - GET /producers/{id}
    - POST /producers
    - PUT /producers/{id}
    - DELETE /producers/{id}

Outros endpoints

- `GET /producers/{producerId}/films` - para listar todos os filmes produzidos por um produtor específico
- `POST /producers/{producerId}/films/{filmId}` - para relacionar um produtor já registrado no banco a um filme

### *Soundtrack*:

Rotas de criação, update e delete

    - POST /soundtracks
    - PUT /soundtracks/{id}
    - DELETE /soundtracks/{id}

Outros endpoints

- `GET /soundtracks/search` - pesquisar a trilha sonora pelo nome do compositor
- `POST /soundtracks/{id}/tracks` - adiciona faixas a uma trilha sonora específica
- `DELETE /soundtracks/{soundtrackId}/tracks/{trackId}` - exclui uma faixa específica de uma trilha sonora

### *Review*:

Rotas de criação e delete

    - POST /reviews
    - DELETE /reviews/{id}

Outros endpoints

- `GET /reviews/{filmId}` - exibe todas as críticas de um filme específico
- `GET /reviews/{filmId}/rating` - calcula a média de notas recebidas nas críticas de um filme

### *Award*:

Criação

    - POST /awards

Outros endpoints

- `GET /awards/{filmId}` - exibe todos os prêmios que um filme recebeu
- `GET /awards/search` - pesquisar por todos os prêmios e seus ganhadores de um ano específico