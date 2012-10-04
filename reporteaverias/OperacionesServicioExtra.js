
function DetalleServicioextra(row) {
    d3.json("http://localhost:8089/rest/serviciosextra/"+row.id, function(servicioextra) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
      var div = content.append("div");
      div.append("input")
        .attr("value",servicioextra.id)
        .attr("id","id").
        attr("type","hidden");
      div.append("h2").text("Detalle de servicioextra");
      var table = div.append("table");
      var tr = table.append("tr");
tr.append("td").text("Nombre:");
tr.append("td").append("input")
.attr("value",servicioextra.nombre)
.attr("id","nombre");
tr.append("td").text("Servicio:");
tr.append("td").append("input")
.attr("value",servicioextra.servicio)
.attr("id","servicio");
      div.append("input").attr("type","button").attr("value","Actualizar").attr("id","button").attr("onClick","ActualizarServicioextra(this)");
      div.append("input").attr("type","button").attr("value","Eliminar").attr("id","button").attr("onClick","EliminarServicioextra(this)");
    });
  }
  function AgregarServicioextra(row) {
    d3.json("http://localhost:8089/rest/serviciosextra/nuevo", function(json) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
	  var idAleatorio = Math.floor(Math.random()*5000)
      var div = content.append("div");
      div.append("input")
        .attr("value",idAleatorio)
        .attr("id","id").
attr("type","hidden");
      div.append("h2").text("Nuevo servicioextra");
      var table = div.append("table");
      var tr = table.append("tr");
tr.append("td").text("Nombre:");
tr.append("td").append("input")
.attr("value","")
.attr("id","nombre");
tr.append("td").text("Servicio:");
tr.append("td").append("input")
.attr("value","")
.attr("id","servicio");

      div.append("input").attr("type","button").attr("value","Actualizar").attr("id","button").attr("onClick","ActualizarServicioextra(this)");
    });
  }

function EliminarServicioextra() {
var id  = d3.select("#id").property("value");
var nombre  = d3.select("#nombre").property("value");
var servicio  = d3.select("#servicio").property("value");
var params = "id="+id+"&nombre="+nombre+"&servicio="+servicio;
d3.xhr2("http://localhost:8089/rest/serviciosextra/"+id,"DELETE",params,function(json) {
    });
ListadoServiciosextra();
  }

d3.xhr2 = function(url, method, params, mime, callback) {
    var req = new XMLHttpRequest;
    if (arguments.length < 5) callback = mime, mime = null;
    else if (mime && req.overrideMimeType) req.overrideMimeType(mime);
    req.open(method, url, true);
    if (mime) req.setRequestHeader("Accept", mime);
    req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    req.setRequestHeader("Content-length", params.length);
    req.setRequestHeader("Connection", "close");
    req.onreadystatechange = function() {
      if (req.readyState === 4) {
        var s = req.status;
        callback(s >= 200 && s < 300 || s === 304 ? req : null);
      }
    };
    req.send(params);
  };

function ActualizarServicioextra() {
var id  = d3.select("#id").property("value");
var nombre  = d3.select("#nombre").property("value");
var servicio  = d3.select("#servicio").property("value");
var params = "id="+id+"&nombre="+nombre+"&servicio="+servicio;
d3.xhr2("http://localhost:8089/rest/serviciosextra/"+id,"PUT",params,function(json) {
    });
ListadoServiciosextra();
  }

  function AgregarServicioextra(row) {
    d3.json("http://localhost:8089/rest/serviciosextra/nuevo", function(json) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
	  var idAleatorio = Math.floor(Math.random()*5000)
      var div = content.append("div");
      div.append("input")
        .attr("value",idAleatorio)
        .attr("id","id").
attr("type","hidden");
      div.append("h2").text("Nuevo servicioextra");
      var table = div.append("table");
      var tr = table.append("tr");
tr.append("td").text("Nombre:");
tr.append("td").append("input")
.attr("value","")
.attr("id","nombre");
tr.append("td").text("Servicio:");
tr.append("td").append("input")
.attr("value","")
.attr("id","servicio");

      div.append("input").attr("type","button").attr("value","Actualizar").attr("id","button").attr("onClick","ActualizarServicioextra(this)");
    });
  }

  function ListadoServiciosextra() {
    d3.json("http://localhost:8089/rest/serviciosextra",function(json) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
      var div = content.append("div");
      div.append("h2").text("Listado de serviciosextra");
      var table = div.append("table");
      var thead = table.append("thead");
      thead.append("th").text("Id");
      thead.append("th").text("Nombre");
      thead.append("th").text("Servicio");
      var tr = table.selectAll("tr")
                  .data(json.servicioextra)
                  .enter().append("tr")
                  .attr("onClick","DetalleServicioextra(this)");
      tr.attr("id",function(d) {return d.id;});
      tr.append("td").text(function(d) {return d.id;});
      tr.append("td").text(function(d) {return d.nombre;});
      tr.append("td").text(function(d) {return d.servicio;});
      div.append("input").attr("type","button").attr("value","Agregar").attr("id","button").attr("onClick","AgregarServicioextra(this)");
    });
  }
