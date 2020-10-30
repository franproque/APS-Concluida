function login(){
    let submit = document.querySelector('.form-menu')

    submit.addEventListener('submit', e =>{
        e.preventDefault();
    })

    let email = document.getElementById('txtEmail').value
    let senha = document.getElementById('txtSenha').value

    //Conectar com backend e trazer valores para validar se o login existe realmente
    const url = "url";
    fetch(url, { 
        method: 'get' // opcional 
      })
      .then(function(response) { 
        // use a resposta 
      })
      .catch(function(err) { console.error(err); });
        
    
}