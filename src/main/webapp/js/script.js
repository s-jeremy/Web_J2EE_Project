function add_product(id_produit, nom_produit, prix_produit, quantiteDispo_produit){

    let panier = localStorage.getItem("panier");
    let products = [];

    if (quantiteDispo_produit==0){
        console.log("Le produit n'est pas disponible!")
        showToast("Le produit n'est pas disponible!");
    }else {
        if (panier == null) {
            let product = {
                id_produit: id_produit,
                nom_produit: nom_produit,
                prix_produit: prix_produit,
                quantite_produit: 1
            }
            products.push(product);
            localStorage.setItem("panier", JSON.stringify(products));
            console.log("Le produit est ajouté pour la première fois!");
            showToast("Le produit a été ajouté au panier!");
        } else {
            let panierProducts = JSON.parse(panier);
            let oldProduct = panierProducts.find((item => item.id_produit == id_produit));
            if (oldProduct != undefined) {
                if (quantiteDispo_produit>oldProduct.quantite_produit){
                    oldProduct.quantite_produit = oldProduct.quantite_produit + 1;
                    panierProducts.map((item) => {
                        if (item.id_produit == oldProduct.id_produit) {
                            item.quantite_produit = oldProduct.quantite_produit;
                        }
                    })
                    localStorage.setItem("panier", JSON.stringify(panierProducts));
                    console.log("La quantité de produit est augmenté!");
                    showToast( "La quantité de produit " + oldProduct.nom_produit + " est augmenté!  Quantité = " + oldProduct.quantite_produit);
                }
                else{
                    console.log("Stock de disponibilité maximale atteint!");
                    showToast("Stock de disponibilité maximale atteint!");
                }
            } else {
                let product = {
                    id_produit: id_produit,
                    nom_produit: nom_produit,
                    prix_produit: prix_produit,
                    quantite_produit: 1
                }
                panierProducts.push(product);
                localStorage.setItem("panier", JSON.stringify(panierProducts));
                console.log("Le produit est ajouté!")
                showToast("Le produit est ajouté au panier!");

            }
        }
        updatePanier();
    }
}


function updatePanier(){

    let panierToString = localStorage.getItem("panier");
    let panier = JSON.parse(panierToString);
    if(panier == null || panier.length == 0){
        console.log("Le panier est vide !");
        $(".cart-items").html(" (0) ");
        $(".cart-body").html("<h3>Aucun produit dans le panier !</h3>");
        $(".checkout-btn").attr('disabled',true); //$(".checkout-btn").addClass('disabled');
    }
    else {
        $(".cart-items").html(`( ${panier.length} )`);
        let table = `
        <table class='table'>
        <thead class='thread-light'>
            <tr>
                <th>Produit</th>
                <th>Prix</th>
                <th>Quantite</th>
                <th>Prix total</th>
                <th>Action</th>
            </tr>
        </thead>
        `;

        let prixTotal = 0;
        panier.map((item) => {
            table += `
                <tr>
                    <td>${item.nom_produit}</td>
                    <td>${item.prix_produit} €</td>
                    <td>${item.quantite_produit}</td>
                    <td>${item.quantite_produit*item.prix_produit} €</td>
                    <td><button class='btn btn-danger btn-sm' onclick="deleteProduct(${item.id_produit})">Supprimer</button></td>
                </tr>
            `
            prixTotal += item.quantite_produit*item.prix_produit;
        })

        table = table + `
            <tr>
                <td colspan="5" class="m-5" style="font-weight:bold">
                    Prix total : ${prixTotal} €
                </td>
            </tr>
        </table>`
        $(".cart-body").html(table);
        $(".checkout-btn").attr('disabled',false);
        console.log('removed');
    }
}

function deleteProduct(product) {

    let panier = JSON.parse(localStorage.getItem("panier"));
    let newPanier = panier.filter((item) => item.id_produit != product);

    localStorage.setItem("panier",JSON.stringify(newPanier));

    updatePanier();
    showToast("Produit retirer !");
}

$(document).ready(function () {
    updatePanier();
})

function showToast(content){
    $("#toast").addClass("display");
    $("#toast").html(content);
    setTimeout(() => {
        $("#toast").removeClass("display");
    }, 2000);
}

function checking(){
    let panierToString = localStorage.getItem("panier");
    let panier = JSON.parse(panierToString);
    if (panier!=null) {
        window.location = "checking.jsp";
    }
}