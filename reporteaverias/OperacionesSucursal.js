
function DetalleSucursal(row) {
    d3.json("http://localhost:8089/rest/sucursales/"+row.id, function(sucursal) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
      var div = content.append("div");
      div.append("input")
        .attr("value",sucursal.id)
        .attr("id","id").
        attr("type","hidden");
      div.append("h2").text("Detalle de sucursal");
      var table = div.append("table");
      var tr = table.append("tr");
tr.append("td").text("Telefono:");
tr.append("td").append("input")
.attr("value",sucursal.telefono)
.attr("id","telefono");
tr.append("td").text("Ciudad:");
tr.append("td").append("input")
.attr("value",sucursal.ciudad)
.attr("id","ciudad");
tr.append("td").text("Direccion:");
tr.append("td").append("input")
.attr("value",sucursal.direccion)
.attr("id","direccion");
tr.append("td").text("Encargado:");
tr.append("td").append("input")
.attr("value",sucursal.encargado)
.attr("id","encargado");
      div.append("input").attr("type","button").attr("value","Actualizar").attr("id","button").attr("onClick","ActualizarSucursal(this)");
      div.append("input").attr("type","button").attr("value","Eliminar").attr("id","button").attr("onClick","EliminarSucursal(this)");
    });
  }
  function AgregarSucursal(row) {
    d3.json("http://localhost:8089/rest/sucursales/nuevo", function(json) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
	  var idAleatorio = Math.floor(Math.random()*5000)
      var div = content.append("div");
      div.append("input")
        .attr("value",idAleatorio)
        .attr("id","id").
attr("type","hidden");
      div.append("h2").text("Nuevo sucursal");
      var table = div.append("table");
      var tr = table.append("tr");
tr.append("td").text("Telefono:");
tr.append("td").append("input")
.attr("value","")
.attr("id","telefono");
tr.append("td").text("Ciudad:");
tr.append("td").append("input")
.attr("value","")
.attr("id","ciudad");
tr.append("td").text("Direccion:");
tr.append("td").append("input")
.attr("value","")
.attr("id","direccion");
tr.append("td").text("Encargado:");
tr.append("td").append("input")
.attr("value","")
.attr("id","encargado");

      div.append("input").attr("type","button").attr("value","Actualizar").attr("id","button").attr("onClick","ActualizarSucursal(this)");
    });
  }

function EliminarSucursal() {
var id  = d3.select("#id").property("value");
var telefono  = d3.select("#telefono").property("value");
var ciudad  = d3.select("#ciudad").property("value");
var direccion  = d3.select("#direccion").property("value");
var encargado  = d3.select("#encargado").property("value");
var params = "id="+id+"&telefono="+telefono+"&ciudad="+ciudad+"&direccion="+direccion+"&encargado="+encargado;
d3.xhr2("http://localhost:8089/rest/sucursales/"+id,"DELETE",params,function(json) {
    });
ListadoSucursales();
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

function ActualizarSucursal() {
var id  = d3.select("#id").property("value");
var telefono  = d3.select("#telefono").property("value");
var ciudad  = d3.select("#ciudad").property("value");
var direccion  = d3.select("#direccion").property("value");
var encargado  = d3.select("#encargado").property("value");
var params = "id="+id+"&telefono="+telefono+"&ciudad="+ciudad+"&direccion="+direccion+"&encargado="+encargado;
d3.xhr2("http://localhost:8089/rest/sucursales/"+id,"PUT",params,function(json) {
    });
ListadoSucursales();
  }

  function AgregarSucursal(row) {
    d3.json("http://localhost:8089/rest/sucursales/nuevo", function(json) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
	  var idAleatorio = Math.floor(Math.random()*5000)
      var div = content.append("div");
      div.append("input")
        .attr("value",idAleatorio)
        .attr("id","id").
attr("type","hidden");
      div.append("h2").text("Nuevo sucursal");
      var table = div.append("table");
      var tr = table.append("tr");
tr.append("td").text("Telefono:");
tr.append("td").append("input")
.attr("value","")
.attr("id","telefono");
tr.append("td").text("Ciudad:");
tr.append("td").append("input")
.attr("value","")
.attr("id","ciudad");
tr.append("td").text("Direccion:");
tr.append("td").append("input")
.attr("value","")
.attr("id","direccion");
tr.append("td").text("Encargado:");
tr.append("td").append("input")
.attr("value","")
.attr("id","encargado");

      div.append("input").attr("type","button").attr("value","Actualizar").attr("id","button").attr("onClick","ActualizarSucursal(this)");
    });
  }

  function ListadoSucursales() {
    d3.json("http://localhost:8089/rest/sucursales",function(json) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
      var div = content.append("div");
      div.append("h2").text("Listado de sucursales");
      var table = div.append("table");
      var thead = table.append("thead");
      thead.append("th").text("Id");
      thead.append("th").text("Telefono");
      thead.append("th").text("Ciudad");
      thead.append("th").text("Direccion");
      thead.append("th").text("Encargado");
      var tr = table.selectAll("tr")
                  .data(json.sucursal)
                  .enter().append("tr")
                  .attr("onClick","DetalleSucursal(this)");
      tr.attr("id",function(d) {return d.id;});
      tr.append("td").text(function(d) {return d.id;});
      tr.append("td").text(function(d) {return d.telefono;});
      tr.append("td").text(function(d) {return d.ciudad;});
      tr.append("td").text(function(d) {return d.direccion;});
      tr.append("td").text(function(d) {return d.encargado;});
      div.append("input").attr("type","button").attr("value","Agregar").attr("id","button").attr("onClick","AgregarSucursal(this)");
    });
  }
