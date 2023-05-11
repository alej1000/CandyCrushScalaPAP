# Choose our version of Python
FROM python:3.9

# Set up a working directory
WORKDIR /code

# Copy just the requirements into the working directory so it gets cached by itself
COPY ./requirements.txt /code/requirements.txt

# Install the dependencies from the requirements file
RUN pip install --no-cache-dir --upgrade -r /code/requirements.txt

# Copy the code into the working directory
COPY ./app /code/app

WORKDIR /code/app

# Tell uvicorn to start spin up our code, which will be running inside the container now

CMD ["uvicorn", "--app-dir=BACK/", "main:app", "--host", "0.0.0.0", "--port", "80"]

# CMD ["uvicorn", "app.main:app", "--host", "0.0.0.0", "--port", "80"]

# CMD ["uvicorn", "--app-dir=BACK/","main:app", "--host", "0.0.0.0", "--port", "80"]
# CMD ["uvicorn --app-dir=BACK/ main:app --reload"]
# CMD ["uvicorn --app-dir=BACK/ main:app --host 0.0.0.0 --port 80"]
# docker build -t myimage .   
# docker run -d --name mycontainer -p 80:80 myimage