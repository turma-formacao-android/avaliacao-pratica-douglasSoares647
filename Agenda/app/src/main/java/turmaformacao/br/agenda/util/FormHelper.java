package turmaformacao.br.agenda.util;

import android.widget.EditText;

/**
 * Created by Administrador on 15/09/2015.
 */
public final class FormHelper {

    private FormHelper(){
        super();
    }

    public static boolean validateRequired(String requiredMessage, EditText...  editTexts){
        boolean hasRequired = false;
        for(EditText editText : editTexts){
            if(editText.getText().toString().trim().isEmpty()){//verifica se o campo de texto está vazio
                editText.setError((requiredMessage)) ;//seta mensagem de erro na editText
                hasRequired = true;
            }
        }
        return hasRequired;
    }


}
