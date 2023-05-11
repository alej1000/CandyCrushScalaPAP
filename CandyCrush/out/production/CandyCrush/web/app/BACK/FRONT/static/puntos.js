// Define una variable global baseUrl que contiene el inicio de la URL
const baseUrl = `${window.location.protocol}//${window.location.host}`;

function cargarDatos(){
  //obtiene los valores de los records generando un boton con la informacion de cada uno
  //obtenemos el id de usuario, será el único parametro de la url
  const url = new URL(window.location.href);
  const puntuacion = url.pathname.split("/")[2];
  console.log(puntuacion); // Output: 8


  fetch(`${baseUrl}/records/${puntuacion}`)
    .then(response => response.json())
    .then(data => {
      // console.log(data);
      records = data.data;
      console.log(records);
      nombre = records['nombre'];
      puntos = records.puntuacion;
      fecha = records.fecha;
      duracion = records.duracion;
      imagen = records.picture;

      // Convert the date string to a Date object
      const date = new Date(fecha);

      // Get the day, month and year components
      const day = date.getDate();
      const month = date.getMonth() + 1;
      const year = date.getFullYear();

      // Format the date components as a string in the DD-MM-YYYY format
      const formattedDate = `${day.toString().padStart(2, '0')}-${month.toString().padStart(2, '0')}-${year}`;

      // Get the hour, minute and second components
      const hour = date.getHours();
      const minute = date.getMinutes();
      const second = date.getSeconds();

      // Format the time components as a string in the HH:MM:SS format
      const formattedTime = `${hour.toString().padStart(2, '0')}:${minute.toString().padStart(2, '0')}:${second.toString().padStart(2, '0')}`;
      //añadimos el nombre del usuario a el elemento con id "header"
      // document.getElementById('header').innerHTML = document.getElementById('header').innerHTML + " "+nombre;
      //añadimos la puntuacion a el elemento con id "puntuacion"
      document.getElementById('name').innerHTML = "El jugador/a "+nombre;
      document.getElementById('points').innerHTML = "Obtuvo una puntuación de "+puntos;
      document.getElementById('date').innerHTML = "El día "+formattedDate;
      document.getElementById('time').innerHTML = "A las "+formattedTime;
      document.getElementById('duration').innerHTML = "Jugó durante "+duracion+" segundos";
      if (imagen != null){
        document.getElementById('img').src = imagen;
      }
    }
    )
}

//llamamos a cargarDatos al cargar la pagina
window.onload = cargarDatos();


// Crea una función para iniciar sesión
function iniciarSesion(user, pass) {
  console.log(`Usuario ${user} y contraseña ${pass}`);
  console.log("Justo antes del fetch");

  fetch(`${baseUrl}/login`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({
      correo: user,
      password: pass
    })
  })
    .then(response => response.json())
    .then(data => {
      console.log(data);
      if (data.code === 200) {
        // Guarda el token en el local storage
        // Guardar valores en local storage
        // localStorage.setItem('correo', data['correo']);
        // localStorage.setItem('id_alumno', data['id_alumno']);
        document.cookie = `correo=${data.data.correo}`;
        document.cookie = `id_alumno=${data.data['id_alumno']}`;

        //obtiene los valores de las cookies
        var correo = document.cookie.replace(/(?:(?:^|.*;\s*)correo\s*\=\s*([^;]*).*$)|^.*$/, "$1");
        var id_alumno = document.cookie.replace(/(?:(?:^|.*;\s*)id_alumno\s*\=\s*([^;]*).*$)|^.*$/, "$1");

        console.log(correo);
        console.log(id_alumno);

        // Usa pushState() para agregar la URL actual al historial del navegador
        window.history.pushState({}, null, `${window.location.href}`);
        // Usa replace() para cambiar a la página de menú
        window.location.replace(`${baseUrl}/menu`);
      }
       else {
        document.getElementById('errorInicioSesion').style.display = 'block';
      }
    })
    .catch(error => console.error(error));
}
