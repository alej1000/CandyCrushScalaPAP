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
      console.log(data);
      records = data.data;
      nombre = records['nombre'];
      //añadimos el nombre del usuario a el elemento con id "header"
      document.getElementById('header').innerHTML = `Hola ${records[0].nombre}!`;
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
