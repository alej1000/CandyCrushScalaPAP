from fastapi import FastAPI

app = FastAPI()

@app.get("/")
async def root():
    return {"message": "Hello World"}

@app.get("/bye")
async def bye():
    return {"message": "Bye World"}
