// Define una variable global baseUrl que contiene el inicio de la URL
const baseUrl = `${window.location.protocol}//${window.location.host}`;

function cargarDatos(){
  //obtiene los valores de los records generando un boton con la informacion de cada uno
  fetch(`${baseUrl}/records`)
    .then(response => response.json())
    .then(data => {
      console.log(data);
      var records = data.data;
      var html = '';
      for (var i = 0; i < records.length; i++) {
        nombre = records[i].nombre;
        puntos = records[i].puntuacion;
        fecha = records[i].fecha;
        duracion = records[i].duracion;

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

        console.log(formattedDate); // Outputs: '10-05-2023'
        console.log(formattedTime); // Outputs: '19:47:05'
        html += `<button class="btn btn-primary" onclick="window.location.href = 'puntuacion/${records[i].id_player}'">${nombre}  |  ${puntos}  |  ${formattedDate}</button><br>`;
      }
      document.getElementById('records').innerHTML = html;
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
