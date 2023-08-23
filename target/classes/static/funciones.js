Vue.component("modal", {
    template: "#modal-template"
});

new Vue({
    el: "#app",
    data: {
        showModal: false
    }
});

function mostrarFormulario() {
    $("#pago").hide();
    $("#detalleDelProducto").hide();
    $("#ingresoDeDatos").show();
}

function mostrarBotonDePago() {
    llamar();
    $("#ingresoDeDatos").hide();
    $("#pago").show();
}

function mostrarBotonDePago(data) {
    idPreference = data.PreferenceID;
    const mp = new MercadoPago('APP_USR-ff96fe80-6866-4888-847e-c69250754d38');
    const bricksBuilder = mp.bricks();

    mp.bricks().create("wallet", "wallet_container", {
        initialization: {
            preferenceId: idPreference,
        },
        customization: {
            texts: {
                action: 'pay',
                valueProp: 'security_safety',
            },
            visual: {
                buttonBackground: 'black',
                borderRadius: '16px',
            }
        }
    });
    $("#wallet_container").show();
}

function enviarDatosYMostrarBotonDePago(datos) {

    $.ajax({
        url: "http://localhost:8080/pago",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(datos)
    })
        .done(function (data) {
            mostrarBotonDePago(data);
        })
        .fail(function (a, b, c) {
            console.log(a, b, c);
        });

}

function obtenerDataItem() {
    let form = document.getElementById('dataItem');
    let data = new FormData(form);
    let datosItem = {};

    for (var [key, value] of data) {
        datosItem[key] = value;
    }

    return datosItem;
}

function crearJsonDatosCliente() {

    let form = document.getElementById('ingresoDeDatos');
    let data = new FormData(form);
    let datosCliente = {};
    datosCliente["address"] = {};
    datosCliente["identification"] = {};
    datosCliente["phone"] = {};

    for (var [key, value] of data) {

        if (key === "numberPhone") {
            datosCliente["phone"]["number"] = value;
        } else if (key === "areaCode") {
            datosCliente["phone"][key] = value;
        } else if (key === "numberId") {
            datosCliente["identification"]["number"] = value;
        } else if (key === "type") {
            datosCliente["identification"][key] = value;
        } else if (["streetName", "streetNumber", "zipCode"].includes(key)) {
            datosCliente["address"][key] = value;
        } else {
            datosCliente[key] = value;
        }
    }

    return datosCliente;
}

function mostrarloader() {
    $("#ingresoDeDatos").hide();
    $("#tituloModal").html("Compra");
    $("#botonSiguiente").hide();
    $(".modal-body").css("height", "100px");
    $("#wallet_container").empty();
    $("#loader").show();
}

function generarBotonDePago() {
    let datos = {};
    datos["item"] = obtenerDataItem();
    datos["payer"] = crearJsonDatosCliente();
    mostrarloader();
    enviarDatosYMostrarBotonDePago(datos);
}



