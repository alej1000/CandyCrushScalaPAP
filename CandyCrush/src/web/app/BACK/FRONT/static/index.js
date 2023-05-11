// Define una variable global baseUrl que contiene el inicio de la URL
const baseUrl = `${window.location.protocol}//${window.location.host}`;

function cargarDatos(cadena){
  //obtiene los valores de los records generando un boton con la informacion de cada uno
  fetch(`${baseUrl}/records${cadena}`)
    .then(response => response.json())
    .then(data => {
      console.log(data);
      mostrarDatos(data);
      
    }
    )
}

//llamamos a cargarDatos al cargar la pagina
window.onload = cargarDatos("");

//funcion que recibe el json y lo muestra en la pagina
function mostrarDatos(data){
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

    html += `<button class="btn btn-primary" onclick="window.location.href = 'puntuacion/${records[i].id_player}'">${nombre}  |  ${puntos}  |  ${formattedDate}</button><br>`;
  }
  document.getElementById('records').innerHTML = html;
}
