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