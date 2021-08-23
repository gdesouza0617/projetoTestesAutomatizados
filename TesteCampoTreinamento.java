import org.junit.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class TesteCampoTreinamento {

    private WebDriver driver; //Transformar em uma variável global, aí preciso usar só o driver ao invés do WebDriver nos códigos.

    @Before //Indica que sempre antes de iniciar qualquer teste tem que executar os comandos abaixo.
    public void inicializa(){ //Como todos os testes precisam disso para iniciar, criamos um método para aplicar isso em todos os testes para não precisar ficar repetindo em todos.

        driver = new ChromeDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

    }

    @After
    public void finaliza(){

        driver.quit();

    }


    @Test

    public void testeTextField() { //TextField geralmente são testes com somente 1 linha;
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Gabriel Feijão"); //findElement é usado para buscar um elemento no browser e sendKeys usado para inserir algo dentro desse elemento;
        Assert.assertEquals("Gabriel Feijão", driver.findElement(By.id("elementosForm:nome")).getAttribute("value")); //getAttribute é usado para validar se o texto foi escrito dentro do campo mencionado;


    }

    @Test
    public void deveInteragirTextArea() { //TextArea geralmente são testes com mais de 1 linha;
        driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Gabriel Feijão\nIdade: 19"); // \n é usado para ppular linhas;
        Assert.assertEquals("Gabriel Feijão\nIdade: 19", driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));

    }


    @Test
    public void deveInteragirComRadioButton() { // Botão de marcar ou de opção;
        driver.findElement(By.id("elementosForm:sexo:0")).click(); // click é somente para clicar no elemento selecionado;
        Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected()); //AssertTrue é para booleano, True or False. E os isSelected é para verificar se o botão está clicado;

    }

    @Test
    public void deveInteragirComCheckbox() { // Botão de caixa de seleção;
        driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
        Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());
    }

    @Test
    public void deveInterarirComCombo() { //Várias opções de uma lista
        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(element);
        // combo.selectByIndex(2); //1º forma de selecionar uma opção do combo;
        // combo.selectByValue("superior"); //2º forma de selecionar uma opção do combo;
        combo.selectByVisibleText("2o grau completo"); //3º e melhor forma de selecionar uma opção do combo;
        Assert.assertEquals("2o grau completo", combo.getFirstSelectedOption().getText());


    }

    @Test
    public void deveVerificarValoresCombo() { //Várias opções de uma lista
        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(element); //Encontra o combo e transforma em uma instância do select.
        List<WebElement> options = combo.getOptions(); //Retorna com uma lista de todas as opções do combo.
        Assert.assertEquals(8, options.size()); //Teste para verificar se na lista tem 8 opções.

        boolean encontrou = false; // Teste para verificar se temos alguma opção específica no combo.
        for (WebElement option : options) {
            if (option.getText().equals("Mestrado")) {
                encontrou = true;
                break;
            }
        }
        Assert.assertTrue(encontrou);



    }

    @Test
    public void deveVerificarValoresComboMultiplos() { //Várias opções de uma lista
        WebElement element = driver.findElement(By.id("elementosForm:esportes"));
        Select combo = new Select(element);
        combo.selectByVisibleText("Natacao");
        combo.selectByVisibleText("Corrida");
        combo.selectByVisibleText("O que eh esporte?");

        List<WebElement> allSelectedOptions = combo.getAllSelectedOptions(); //Vai listar as opções
        Assert.assertEquals(3, allSelectedOptions.size()); // Vai verificar se realmente estão 3 opções selecionadas.

        combo.deselectByVisibleText("Corrida"); // Para tirar a opção selecionada e verificar novamente.
        allSelectedOptions = combo.getAllSelectedOptions(); //Vai listar as
        Assert.assertEquals(2, allSelectedOptions.size());



    }

    @Test
    public void deveInteragirComBotoes() { //Teste para botões que mudam de nome ao clicar.
        WebElement botao = driver.findElement(By.id("buttonSimple"));
        botao.click();

        Assert.assertEquals("Obrigado!", botao.getAttribute("value"));

    }

    @Test
    // @Ignore -> 1º Maneira de evitar que o teste seja executado, ele não vai nem executar.
    public void deveInteragirComLinks() { //Teste para botões que mudam de nome ao clicar.

        driver.findElement(By.linkText("Voltar")).click();

        Assert.assertEquals("Voltou!", driver.findElement(By.id("resultado")).getText());


        // Assert.fail(); -> 2º Maneira de evitar que o teste seja executado, essa vai dá falha no final.


    }

    @Test
    public void deveBuscarTextosNaPagina() {

        Assert.assertEquals("Campo de Treinamento", driver.findElement(By.tagName("h3")).getText());

        Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", driver.findElement(By.className("facilAchar")).getText());

       // Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Campo de Treinamento")); //2º forma de verificar texto, ela pega o body, que é tudo que está escrito no html da página, e verifica com o contains se temos o texto específico lá. E dessa forma não temos o controle de onde o texto está, somente verificamos se está na página.


    }



    }
