function add_product(id_produit, nom_produit, prix_produit){

    let panier = localStorage.getItem("panier");
    let products = [];

    if(panier == null){
        let product = {
            id_produit: id_produit,
            nom_produit: nom_produit,
            prix_produit: prix_produit,
            quantite_produit: 1
        }
        products.push(product);
        localStorage.setItem("panier",JSON.stringify(products));
        console.log("Le produit est ajouté pour la première fois!");
    }
    else{
        let panierProducts = JSON.parse(panier);
        let oldProduct = panierProducts.find((item => item.id_produit = id_produit));
        if(oldProduct){
            oldProduct.quantite_produit = oldProduct.quantite_produit + 1;
            panierProducts.map((item) => {
                if(item.id_produit == oldProduct.id_produit){
                    item.quantite_produit = oldProduct.quantite_produit;
                }
            })
            localStorage.setItem("panier",JSON.stringify(panierProducts));
            console.log("La quantité de produit est augmenté !");
        }
        else{
            let product = {
                id_produit: id_produit,
                nom_produit: nom_produit,
                prix_produit: prix_produit,
                quantite_produit: 1
            }
            products.push(product);
            localStorage.setItem("panier",JSON.stringify(panierProducts));
            console.log("Le produit est ajouté")
        }
    }
}


function updatePanier(){

    let panierToString = localStorage.getItem("panier");
    let panier = JSON.parse(panierToString);
    if(panier == null || panier.length == null){
        console.log("Le panier est vide !");
        $(".cart-items").html(" (0) ");
        $(".cart-body").html("<h3>Aucun produit dans le panier !</h3>");
        $(".checkout-btn").addClass('disabled');
    }
    else {
        console.log(panier);
        $(".cart-items").html(`( ${panier.length} )`);
        let table = `
        <table class='table'>
        <thread class='thread-light'>
            <tr>
                <th>Produit</th>
                <th>Prix</th>
                <th>Quantite</th>
                <th>Prix total</th>
                <th>Action</th>
            </tr>
        </thread>
        `;

        let prixTotal = 0;
        panier.map((item) => {
            table += `
                <tr>
                    <td>{item.nom_produit}</td>
                    <td>{item.prix_produit}</td>
                    <td>{item.quantite_produit}</td>
                    <td>{item.quantite_produit*item.prix_produit}</td>
                    <td><button class='btn btn-danger btn-sm'>Supprimer</button></td>
                </tr>
            `
            prixTotal += item.quantite_produit*item.prix_produit;
        })

        table = table + `
            <tr>
                <td colspan="5" class="text-right font-weight-bold m-5">
                    Prix total : ${prixTotal} 
                </td>
            </tr>
        </table>`
        $(".cart-body").html(table);
    }
}

$(document).ready(function () {
    updatePanier();
})