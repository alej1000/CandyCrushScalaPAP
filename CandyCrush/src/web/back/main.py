from fastapi import FastAPI, Request
from fastapi.exceptions import RequestValidationError
from fastapi.responses import JSONResponse
from starlette.exceptions import HTTPException
from fastapi.staticfiles import StaticFiles
from endpoints import records, paginasHTML
app = FastAPI()

# Rutas para puntos
app.include_router(records.router)

# Rutas para paginas HTML
app.include_router(paginasHTML.router)

#Mount the static files directory at "/static"
# app.mount("/static", StaticFiles(directory="FRONT/static"), name="static")