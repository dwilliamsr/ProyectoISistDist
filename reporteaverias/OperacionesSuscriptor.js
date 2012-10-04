
function DetalleSuscriptor(row) {
    d3.json("http://localhost:8089/rest/suscriptores/"+row.id, function(suscriptor) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
      var div = content.append("div");
      div.append("input")
        .attr("value",suscriptor.id)
        .attr("id","id").
        attr("type","hidden");
      div.append("h2").text("Detalle de suscriptor");
      var table = div.append("table");
      var tr = table.append("tr");
tr.append("td").text("Nombre:");
tr.append("td").append("input")
.attr("value",suscriptor.nombre)
.attr("id","nombre");
tr.append("td").text("Identificacion:");
tr.append("td").append("input")
.attr("value",suscriptor.identificacion)
.attr("id","identificacion");
tr.append("td").text("Telefono:");
tr.append("td").append("input")
.attr("value",suscriptor.telefono)
.attr("id","telefono");
tr.append("td").text("Direccion:");
tr.append("td").append("input")
.attr("value",suscriptor.direccion)
.attr("id","direccion");
      div.append("input").attr("type","button").attr("value","Actualizar").attr("id","button").attr("onClick","ActualizarSuscriptor(this)");
      div.append("input").attr("type","button").attr("value","Eliminar").attr("id","button").attr("onClick","EliminarSuscriptor(this)");
    });
  }
  function AgregarSuscriptor(row) {
    d3.json("http://localhost:8089/rest/suscriptores/nuevo", function(json) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
	  var idAleatorio = Math.floor(Math.random()*5000)
      var div = content.append("div");
      div.append("input")
        .attr("value",idAleatorio)
        .attr("id","id").
attr("type","hidden");
      div.append("h2").text("Nuevo suscriptor");
      var table = div.append("table");
      var tr = table.append("tr");
tr.append("td").text("Nombre:");
tr.append("td").append("input")
.attr("value","")
.attr("id","nombre");
tr.append("td").text("Identificacion:");
tr.append("td").append("input")
.attr("value","")
.attr("id","identificacion");
tr.append("td").text("Telefono:");
tr.append("td").append("input")
.attr("value","")
.attr("id","telefono");
tr.append("td").text("Direccion:");
tr.append("td").append("input")
.attr("value","")
.attr("id","direccion");

      div.append("input").attr("type","button").attr("value","Actualizar").attr("id","button").attr("onClick","ActualizarSuscriptor(this)");
    });
  }

function EliminarSuscriptor() {
var id  = d3.select("#id").property("value");
var nombre  = d3.select("#nombre").property("value");
var identificacion  = d3.select("#identificacion").property("value");
var telefono  = d3.select("#telefono").property("value");
var direccion  = d3.select("#direccion").property("value");
var params = "id="+id+"&nombre="+nombre+"&identificacion="+identificacion+"&telefono="+telefono+"&direccion="+direccion;
d3.xhr2("http://localhost:8089/rest/suscriptores/"+id,"DELETE",params,function(json) {
    });
ListadoSuscriptores();
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

function ActualizarSuscriptor() {
var id  = d3.select("#id").property("value");
var nombre  = d3.select("#nombre").property("value");
var identificacion  = d3.select("#identificacion").property("value");
var telefono  = d3.select("#telefono").property("value");
var direccion  = d3.select("#direccion").property("value");
var params = "id="+id+"&nombre="+nombre+"&identificacion="+identificacion+"&telefono="+telefono+"&direccion="+direccion;
d3.xhr2("http://localhost:8089/rest/suscriptores/"+id,"PUT",params,function(json) {
    });
ListadoSuscriptores();
  }

  function AgregarSuscriptor(row) {
    d3.json("http://localhost:8089/rest/suscriptores/nuevo", function(json) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
	  var idAleatorio = Math.floor(Math.random()*5000)
      var div = content.append("div");
      div.append("input")
        .attr("value",idAleatorio)
        .attr("id","id").
attr("type","hidden");
      div.append("h2").text("Nuevo suscriptor");
      var table = div.append("table");
      var tr = table.append("tr");
tr.append("td").text("Nombre:");
tr.append("td").append("input")
.attr("value","")
.attr("id","nombre");
tr.append("td").text("Identificacion:");
tr.append("td").append("input")
.attr("value","")
.attr("id","identificacion");
tr.append("td").text("Telefono:");
tr.append("td").append("input")
.attr("value","")
.attr("id","telefono");
tr.append("td").text("Direccion:");
tr.append("td").append("input")
.attr("value","")
.attr("id","direccion");

      div.append("input").attr("type","button").attr("value","Actualizar").attr("id","button").attr("onClick","ActualizarSuscriptor(this)");
    });
  }

  function ListadoSuscriptores() {
    d3.json("http://localhost:8089/rest/suscriptores",function(json) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
      var div = content.append("div");
      div.append("h2").text("Listado de suscriptores");
      var table = div.append("table");
      var thead = table.append("thead");
      thead.append("th").text("Id");
      thead.append("th").text("Nombre");
      thead.append("th").text("Identificacion");
      thead.append("th").text("Telefono");
      thead.append("th").text("Direccion");
      var tr = table.selectAll("tr")
                  .data(json.suscriptor)
                  .enter().append("tr")
                  .attr("onClick","DetalleSuscriptor(this)");
      tr.attr("id",function(d) {return d.id;});
      tr.append("td").text(function(d) {return d.id;});
      tr.append("td").text(function(d) {return d.nombre;});
      tr.append("td").text(function(d) {return d.identificacion;});
      tr.append("td").text(function(d) {return d.telefono;});
      tr.append("td").text(function(d) {return d.direccion;});
      div.append("input").attr("type","button").attr("value","Agregar").attr("id","button").attr("onClick","AgregarSuscriptor(this)");
    });
  }
