from fastapi import APIRouter
from fastapi.responses import HTMLResponse
import logica

router = APIRouter()


@router.get("/")
def index():
    """
    Devuelve el index.html
    La página principal donde se encuentran todos los records
    """

    with open("FRONT/index.html", "r", encoding="utf-8") as f:
        html = f.read()
    return HTMLResponse(html)

@router.get("/puntuacion/{id}")
def puntuacion(id: int):
    """
    Devuelve un html con la puntuacion del jugador con el id dado
    """
    with open("FRONT/puntos.html", "r", encoding="utf-8") as f:
        html = f.read()
    return HTMLResponse(html)