import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteGoogle {

    private WebDriver driver; //Transformar em uma variável global, aí preciso usar só o driver ao invés do WebDriver nos códigos.

    @Before //Indica que sempre antes de iniciar qualquer teste tem que executar os comandos abaixo.
    public void inicializa(){ //Como todos os testes precisam disso para iniciar, criamos um método para aplicar isso em todos os testes para não precisar ficar repetindo em todos.

        driver = new ChromeDriver();
    }

    @After
    public void finaliza(){

        driver.quit();

    }



    @Test
    public void teste() {
        driver.get("http://www.google.com"); // Comando para abrir o navegador;
        // Temos o assertEquals que compara 2 elementos;
        // E temos o assertTrue que verifica se uma expressão é verdadeira;
        Assert.assertEquals("Google", driver.getTitle()); //Comando para procurar o título da página;

    }

}
