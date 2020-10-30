function fabricaDeUsuario (nome, senha, senhaConf, email, cpf, telefone, dtNasc) {
    let usuario = {};

    usuario.nome = nome
    usuario.senha = senha
    usuario.senhaConf = senhaConf
    usuario.email = email
    usuario.cpf = cpf
    usuario.telefone = telefone
    usuario.dtNasc = dtNasc

    function validarSenhas(){
        if(senha.lenght < 8){
            alert('Senha muito curta, menor que 8 caracteres');
            return;
        }
       else if(senhaConf != senha){
            alert('Senhas não estão iguais, verifique');
            return;
        }

        return true;
   }

   function validarEmail(){
        if(  email=="" 
            || email.indexOf('@')==-1 
            || email.indexOf('.')==-1 )
        {
           alert( "Por favor, informe um E-MAIL válido!" );
           return false;
        }
        return true;
    }

    function validarCPF() {	
        cpf = cpf.replace(/[^\d]+/g,'');	
        if(cpf == '') return false;	
        // Elimina CPFs invalidos conhecidos	
        if (cpf.length != 11 || 
            cpf == "00000000000" || 
            cpf == "11111111111" || 
            cpf == "22222222222" || 
            cpf == "33333333333" || 
            cpf == "44444444444" || 
            cpf == "55555555555" || 
            cpf == "66666666666" || 
            cpf == "77777777777" || 
            cpf == "88888888888" || 
            cpf == "99999999999"){
                
                alert('CPF inválido')
                return false;	
            }
                	
        // Valida 1o digito	
        add = 0;	
        for (i=0; i < 9; i ++)		
            add += parseInt(cpf.charAt(i)) * (10 - i);	
            rev = 11 - (add % 11);	
            if (rev == 10 || rev == 11)		
                rev = 0;	
            if (rev != parseInt(cpf.charAt(9))){
                alert('CPF Inválido')
                return false;
            }		
                		
        // Valida 2o digito	
        add = 0;	
        for (i = 0; i < 10; i ++)		
            add += parseInt(cpf.charAt(i)) * (11 - i);	
        rev = 11 - (add % 11);	
        if (rev == 10 || rev == 11)	
            rev = 0;	
        if (rev != parseInt(cpf.charAt(10))){
            alert('CPF inválido')
            return false;
        }
            		
        return true;   
    }

    function gerarObjeto(){
        let objJSON = {

            cpf_cnpj: cpf,
            email: email,
            nomeFantasiaNomeCompleto: nome,
            senha: senha,
            telefone: telefone,
            dataNascimento: dtNasc
        }

        return objJSON
    }

    function postUsuario(url, obj){
        fetch(url, {
            method: 'post',
            headers: {
                'Content-Type': 'application/json'
             },
        body: JSON.stringify(obj)
    }).then(res=>res.json())
        .then(res => console.log(res));
    }

    usuario.validarSenhas = validarSenhas
    usuario.validarEmail = validarEmail
    usuario.validarCPF = validarCPF
    usuario.gerarObjeto = gerarObjeto
    usuario.postUsuario = postUsuario

    return usuario
}


