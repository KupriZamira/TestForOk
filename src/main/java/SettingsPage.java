import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SettingsPage {
    private static WebDriver driver;
    private By name = By.xpath("//div/input[@id='field_name']");
    private By surname = By.xpath("//div/input[@id='field_surname']");
    private By submit = By.xpath("//div/input[@id='hook_FormButton_button_savePopLayerEditUserProfileNew']");
    private By gender = By.xpath("*//input[@id='field_gender_2']");
    private By currentCity = By.xpath("//div/input[@id = 'field_citySugg_SearchInput']");
    private By radiobuttonCurrentCity = By.xpath("//div[@class = 'ellip'][position()=1]");
    private By city = By.xpath("//div/input[@id = 'field_cityBSugg_SearchInput']");
    private By radioButtonCity = By.xpath("//div[@class = 'ellip'][(1)]");


    public SettingsPage (WebDriver driver){
        this.driver = driver;
    }

    //проверка на сохранение если пустые поля
    public SettingsPage checkErrorEmptyFields(){
        driver.findElement(name).clear();
        driver.findElement(surname).clear();
        driver.findElement(currentCity).clear();
        driver.findElement(city).clear();
        driver.findElement(submit).click();
        return this;
    }

    // проверка ввода и сохранения неправильных данных
    public SettingsPage checkInputIncorrectData(){
        driver.findElement(name).sendKeys("!@#$%^&*()jhgHHGIАВ34345авАВКАОРО");
        driver.findElement(surname).sendKeys("!@#$%^&*()jhgHHGIАВавАВКАОРОТьлдgrgrtgrhHUHU");
        driver.findElement(currentCity).sendKeys("!@#$%^&*()jhgHHGIАВавАВКАОРОТьлд!@#$%^&*()jhgHHGIАВавАВКА34343д");
        driver.findElement(city).sendKeys("!@#$%^&*()jhgHHGIАВавАВКАОРОТьлд34455");
        driver.findElement(submit).click();
        return this;
    }

    // проверка на ввод в поле имя и фамилия цифр
    public SettingsPage checkInputNumberInNameAndSurname (){
        driver.findElement(name).sendKeys("1");
        driver.findElement(surname).sendKeys("1");
        driver.findElement(submit).click();
        return this;
    }

    // проверка на ввод корректных данных и проверка сохранения
    public SettingsPage inputCorrectData(){
        driver.findElement(name).sendKeys("Лидия");
        driver.findElement(surname).sendKeys("Иванова");
        driver.findElement(currentCity).sendKeys("Москва");
        driver.findElement(radioButtonCity).click();
        driver.findElement(city).sendKeys("Тула");
        driver.findElement(radioButtonCity).click();
        driver.findElement(submit).click();
        return this;
    }

    // метод который делает скриншот в конце тестов
    // в 78 строке кода указать локально, где нужно сохранять файлы
    // нужно заменить пример на свой путь
    public SettingsPage screenshots(){
        Date dateNow = new Date();
        SimpleDateFormat format = new SimpleDateFormat(" hh_mm_ss");
        String project = " Личные данные";
        String fileName = format.format(dateNow) + project + ".png";
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileHandler.copy(screenshot, new File("C:\\Users\\Username\\Desktop\\test1\\screenshot.png" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }
}
