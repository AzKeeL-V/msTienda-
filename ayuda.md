# Endpoints Tienda
http://localhost:8080
| Método HTTP | Ruta                                             | Descripción                                  | Código de respuesta                                                          |
| ----------- | ------------------------------------------------ | -------------------------------------------- | ---------------------------------------------------------------------------- |
| `GET`       | `/api/tiendas`                                   | Listar todas las tiendas.                    | `200 OK`                                                                     |
| `GET`       | `/api/tiendas/{id}`                              | Obtener tienda por su ID.                    | `200 OK` si existe, `404 Not Found` si no.                                   |
| `POST`      | `/api/tiendas`                                   | Crear una nueva tienda.                      | `201 Created`                                                                |
| `PUT`       | `/api/tiendas/{id}`                              | Actualizar una tienda por su ID.             | `200 OK` si existe, `404 Not Found` si no.                                   |
| `DELETE`    | `/api/tiendas/{id}`                              | Eliminar una tienda por su ID.               | `204 No Content` si se elimina, `404 Not Found` si no existe.                |
| `PUT`       | `/api/tiendas/{id}/hora-entrada`                 | Establecer la hora de entrada de una tienda. | `200 OK` (pendiente implementación real)                                     |
| `PUT`       | `/api/tiendas/{id}/hora-salida`                  | Establecer la hora de salida de una tienda.  | `200 OK` (pendiente implementación real)                                     |
| `POST`      | `/api/tiendas/{tiendaId}/politicas/{politicaId}` | Asignar una política a una tienda.           | `200 OK` si se asigna, `404 Not Found` si no se encuentra tienda o política. |
| `DELETE`    | `/api/tiendas/{tiendaId}/politicas/{politicaId}` | Quitar una política de una tienda.           | `200 OK` si se quita, `404 Not Found` si no se encuentra tienda o política.  |


# JSON de tienda
    {
        "idTienda": 2,
        "nomTienda": "Mi Tienda Principal",
        dirTienda": "Avenida Siempre Viva 123",
        "horaEntrada": "09:00:00",
        "horaSalida": "20:00:00",
        "listaPoliticas": []
    }

# Endpoints Politica

| Método HTTP | Ruta                  | Descripción                        | Código de respuesta                                           |
| ----------- | --------------------- | ---------------------------------- | ------------------------------------------------------------- |
| `GET`       | `/api/politicas`      | Listar todas las políticas.        | `200 OK` si hay políticas, `204 No Content` si está vacío.    |
| `GET`       | `/api/politicas/{id}` | Obtener una política por ID.       | `200 OK` si existe, `404 Not Found` si no.                    |
| `POST`      | `/api/politicas`      | Crear una nueva política.          | `201 Created`                                                 |
| `PUT`       | `/api/politicas/{id}` | Actualizar una política por su ID. | `200 OK` si existe, `404 Not Found` si no.                    |
| `DELETE`    | `/api/politicas/{id}` | Eliminar una política por su ID.   | `204 No Content` si se elimina, `404 Not Found` si no existe. |

# JSON de politica

    {
        "idPolitica": null,
        "descripcion": "La mama de la mama de la mama de la mama de la mamaa"
    }