
function DetalleTipovivienda(row) {
    d3.json("http://localhost:8089/rest/tiposvivienda/"+row.id, function(tipovivienda) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
      var div = content.append("div");
      div.append("input")
        .attr("value",tipovivienda.id)
        .attr("id","id").
        attr("type","hidden");
      div.append("h2").text("Detalle de tipovivienda");
      var table = div.append("table");
      var tr = table.append("tr");
tr.append("td").text("Nombre :");
tr.append("td").append("input")
.attr("value",tipovivienda.nombre )
.attr("id","nombre ");
tr.append("td").text("Servicio:");
tr.append("td").append("input")
.attr("value",tipovivienda.servicio)
.attr("id","servicio");
      div.append("input").attr("type","button").attr("value","Actualizar").attr("id","button").attr("onClick","ActualizarTipovivienda(this)");
      div.append("input").attr("type","button").attr("value","Eliminar").attr("id","button").attr("onClick","EliminarTipovivienda(this)");
    });
  }
  function AgregarTipovivienda(row) {
    d3.json("http://localhost:8089/rest/tiposvivienda/nuevo", function(json) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
	  var idAleatorio = Math.floor(Math.random()*5000)
      var div = content.append("div");
      div.append("input")
        .attr("value",idAleatorio)
        .attr("id","id").
attr("type","hidden");
      div.append("h2").text("Nuevo tipovivienda");
      var table = div.append("table");
      var tr = table.append("tr");
tr.append("td").text("Nombre :");
tr.append("td").append("input")
.attr("value","")
.attr("id","nombre ");
tr.append("td").text("Servicio:");
tr.append("td").append("input")
.attr("value","")
.attr("id","servicio");

      div.append("input").attr("type","button").attr("value","Actualizar").attr("id","button").attr("onClick","ActualizarTipovivienda(this)");
    });
  }

function EliminarTipovivienda() {
var id  = d3.select("#id").property("value");
var nombre   = d3.select("#nombre ").property("value");
var servicio  = d3.select("#servicio").property("value");
var params = "id="+id+"&nombre ="+nombre +"&servicio="+servicio;
d3.xhr2("http://localhost:8089/rest/tiposvivienda/"+id,"DELETE",params,function(json) {
    });
ListadoTiposvivienda();
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

function ActualizarTipovivienda() {
var id  = d3.select("#id").property("value");
var nombre   = d3.select("#nombre ").property("value");
var servicio  = d3.select("#servicio").property("value");
var params = "id="+id+"&nombre ="+nombre +"&servicio="+servicio;
d3.xhr2("http://localhost:8089/rest/tiposvivienda/"+id,"PUT",params,function(json) {
    });
ListadoTiposvivienda();
  }

  function AgregarTipovivienda(row) {
    d3.json("http://localhost:8089/rest/tiposvivienda/nuevo", function(json) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
	  var idAleatorio = Math.floor(Math.random()*5000)
      var div = content.append("div");
      div.append("input")
        .attr("value",idAleatorio)
        .attr("id","id").
attr("type","hidden");
      div.append("h2").text("Nuevo tipovivienda");
      var table = div.append("table");
      var tr = table.append("tr");
tr.append("td").text("Nombre :");
tr.append("td").append("input")
.attr("value","")
.attr("id","nombre ");
tr.append("td").text("Servicio:");
tr.append("td").append("input")
.attr("value","")
.attr("id","servicio");

      div.append("input").attr("type","button").attr("value","Actualizar").attr("id","button").attr("onClick","ActualizarTipovivienda(this)");
    });
  }

  function ListadoTiposvivienda() {
    d3.json("http://localhost:8089/rest/tiposvivienda",function(json) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
      var div = content.append("div");
      div.append("h2").text("Listado de tiposvivienda");
      var table = div.append("table");
      var thead = table.append("thead");
      thead.append("th").text("Id");
      thead.append("th").text("Nombre ");
      thead.append("th").text("Servicio");
      var tr = table.selectAll("tr")
                  .data(json.tipovivienda)
                  .enter().append("tr")
                  .attr("onClick","DetalleTipovivienda(this)");
      tr.attr("id",function(d) {return d.id;});
      tr.append("td").text(function(d) {return d.id;});
      tr.append("td").text(function(d) {return d.nombre ;});
      tr.append("td").text(function(d) {return d.servicio;});
      div.append("input").attr("type","button").attr("value","Agregar").attr("id","button").attr("onClick","AgregarTipovivienda(this)");
    });
  }
