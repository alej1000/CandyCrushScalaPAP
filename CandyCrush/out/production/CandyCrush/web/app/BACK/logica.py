from decimal import Decimal
from fastapi import HTTPException
import json
import db


#region Funciones de respuesta
class CustomException(Exception):
    def __init__(self, message, code):
        super().__init__(message)
        self.code = code

def respuesta_exitosa(data):
    return {"success": True, "code": 200, "message": "OK", "data": data}

def respuesta_fallida(mensaje, code=400):
    raise CustomException(message=mensaje, code=code)

#endregion


def obtener_records(orden=0, filtro=""):
    query = "SELECT * FROM scores"

    params = []
    if filtro != "":
        query += " WHERE nombre LIKE %s"
        params.append(f"%{filtro}%")
    if orden == 0:
        query += " ORDER BY fecha DESC"
    elif orden == 1:
        query += " ORDER BY puntuacion DESC"

    records = db.realizar_consulta(query, params)
    return records


def obtener_record(id: int):
    query = "SELECT * FROM scores WHERE id_player = %s"
    parameters = ([id])
    record = db.realizar_consulta(query, params=parameters)
    if len(record) == 0:
        raise HTTPException(status_code=404, detail="El record no existe")
    record = record[0]
    return record

def insertar_record(data: dict):
    nombre_tabla = "scores"
    record = db.realizar_insercion(nombre_tabla, data)
    return record

