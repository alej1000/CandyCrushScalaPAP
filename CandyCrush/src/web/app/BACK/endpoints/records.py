# todo esto es necesario para que funcione cualquier .py que se encuentre en la carpeta endpoints
from fastapi import APIRouter, Path, Query, Request
import logica

router = APIRouter() 

# a partir de aquí se definen las rutas


@router.get("/records")
async def get_records(order: int = Query(default=1)):
    """
    Devuelve todos los records en formato json
    """
    #si se recibe query param con order = 2, se ordena por puntos
    #si se recibe query param con order = 1, se ordena por fecha
    #si no se recibe query param, se ordena por fecha

    if order == 1:
        records = logica.obtener_records_orden_puntos()
    else:
        records = logica.obtener_records()
    return logica.respuesta_exitosa(records)

@router.get("/records/arcade")
async def get_records():
    """
    Devuelve todos los records listos para mostrarse en el arcade
    """
    records = logica.obtener_records_orden_puntos()
    stringReturn = ""
    for record in records:
        record["fecha"] = str(record["fecha"])
        record["puntuacion"] = int(record["puntuacion"])
        record["duracion"] = int(record["duracion"])
        stringReturn += str(record) + "@"
    #remove every space
    stringReturn = stringReturn.replace(" ", '')
    #remove the last character
    stringReturn = stringReturn[:-1]

    return stringReturn


@router.get("/records/{id}")
async def get_record(id: int):
    """
    Este endpoint devuelve un record en particular
    """
    try:
        record = logica.obtener_record(id)
        if len(record) == 0:
            raise logica.CustomException(message="No se encontró el record con id_player " + str(id), code=404)
        return logica.respuesta_exitosa(record)
    except logica.CustomException as e:
        raise HTTPException(status_code=e.code, detail=e.message)

@router.post("/records")
async def post_record(request: Request):
    """
    Crear un nuevo record con los datos recibidos en el body
    Retorna el record creado
    """
    #data es un diccionario con los datos que se reciben en el form data
    data = await request.json()
    record = logica.insertar_record(data)
    return logica.respuesta_exitosa(record)

