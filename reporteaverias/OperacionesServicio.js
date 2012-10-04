
function DetalleServicio(row) {
    d3.json("http://localhost:8089/rest/servicios/"+row.id, function(servicio) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
      var div = content.append("div");
      div.append("input")
        .attr("value",servicio.id)
        .attr("id","id").
        attr("type","hidden");
      div.append("h2").text("Detalle de servicio");
      var table = div.append("table");
      var tr = table.append("tr");
tr.append("td").text("FechaInst:");
tr.append("td").append("input")
.attr("value",servicio.fechainst)
.attr("id","fechainst");
tr.append("td").text("Direccion:");
tr.append("td").append("input")
.attr("value",servicio.direccion)
.attr("id","direccion");
tr.append("td").text("Region:");
tr.append("td").append("input")
.attr("value",servicio.region)
.attr("id","region");
tr.append("td").text("Suscriptor:");
tr.append("td").append("input")
.attr("value",servicio.suscriptor)
.attr("id","suscriptor");
      div.append("input").attr("type","button").attr("value","Actualizar").attr("id","button").attr("onClick","ActualizarServicio(this)");
      div.append("input").attr("type","button").attr("value","Eliminar").attr("id","button").attr("onClick","EliminarServicio(this)");
    });
  }
  function AgregarServicio(row) {
    d3.json("http://localhost:8089/rest/servicios/nuevo", function(json) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
	  var idAleatorio = Math.floor(Math.random()*5000)
      var div = content.append("div");
      div.append("input")
        .attr("value",idAleatorio)
        .attr("id","id").
attr("type","hidden");
      div.append("h2").text("Nuevo servicio");
      var table = div.append("table");
      var tr = table.append("tr");
tr.append("td").text("FechaInst:");
tr.append("td").append("input")
.attr("value","")
.attr("id","fechainst");
tr.append("td").text("Direccion:");
tr.append("td").append("input")
.attr("value","")
.attr("id","direccion");
tr.append("td").text("Region:");
tr.append("td").append("input")
.attr("value","")
.attr("id","region");
tr.append("td").text("Suscriptor:");
tr.append("td").append("input")
.attr("value","")
.attr("id","suscriptor");

      div.append("input").attr("type","button").attr("value","Actualizar").attr("id","button").attr("onClick","ActualizarServicio(this)");
    });
  }

function EliminarServicio() {
var id  = d3.select("#id").property("value");
var fechainst  = d3.select("#fechainst").property("value");
var direccion  = d3.select("#direccion").property("value");
var region  = d3.select("#region").property("value");
var suscriptor  = d3.select("#suscriptor").property("value");
var params = "id="+id+"&fechainst="+fechainst+"&direccion="+direccion+"&region="+region+"&suscriptor="+suscriptor;
d3.xhr2("http://localhost:8089/rest/servicios/"+id,"DELETE",params,function(json) {
    });
ListadoServicios();
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

function ActualizarServicio() {
var id  = d3.select("#id").property("value");
var fechainst  = d3.select("#fechainst").property("value");
var direccion  = d3.select("#direccion").property("value");
var region  = d3.select("#region").property("value");
var suscriptor  = d3.select("#suscriptor").property("value");
var params = "id="+id+"&fechainst="+fechainst+"&direccion="+direccion+"&region="+region+"&suscriptor="+suscriptor;
d3.xhr2("http://localhost:8089/rest/servicios/"+id,"PUT",params,function(json) {
    });
ListadoServicios();
  }

  function AgregarServicio(row) {
    d3.json("http://localhost:8089/rest/servicios/nuevo", function(json) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
	  var idAleatorio = Math.floor(Math.random()*5000)
      var div = content.append("div");
      div.append("input")
        .attr("value",idAleatorio)
        .attr("id","id").
attr("type","hidden");
      div.append("h2").text("Nuevo servicio");
      var table = div.append("table");
      var tr = table.append("tr");
tr.append("td").text("FechaInst:");
tr.append("td").append("input")
.attr("value","")
.attr("id","fechainst");
tr.append("td").text("Direccion:");
tr.append("td").append("input")
.attr("value","")
.attr("id","direccion");
tr.append("td").text("Region:");
tr.append("td").append("input")
.attr("value","")
.attr("id","region");
tr.append("td").text("Suscriptor:");
tr.append("td").append("input")
.attr("value","")
.attr("id","suscriptor");

      div.append("input").attr("type","button").attr("value","Actualizar").attr("id","button").attr("onClick","ActualizarServicio(this)");
    });
  }

  function ListadoServicios() {
    d3.json("http://localhost:8089/rest/servicios",function(json) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
      var div = content.append("div");
      div.append("h2").text("Listado de servicios");
      var table = div.append("table");
      var thead = table.append("thead");
      thead.append("th").text("Id");
      thead.append("th").text("FechaInst");
      thead.append("th").text("Direccion");
      thead.append("th").text("Region");
      thead.append("th").text("Suscriptor");
      var tr = table.selectAll("tr")
                  .data(json.servicio)
                  .enter().append("tr")
                  .attr("onClick","DetalleServicio(this)");
      tr.attr("id",function(d) {return d.id;});
      tr.append("td").text(function(d) {return d.id;});
      tr.append("td").text(function(d) {return d.fechainst;});
      tr.append("td").text(function(d) {return d.direccion;});
      tr.append("td").text(function(d) {return d.region;});
      tr.append("td").text(function(d) {return d.suscriptor;});
      div.append("input").attr("type","button").attr("value","Agregar").attr("id","button").attr("onClick","AgregarServicio(this)");
    });
  }
