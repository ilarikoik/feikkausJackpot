package com.feikkaus;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Lotto {
    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\ilari\\Downloads\\chromedriver-win64(1)\\chromedriver-win64\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://www.veikkaus.fi/fi/tulokset");

            // Odotetaan että nappi näkyy sivulla (max 10 sekuntia)
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            // evästeet nappi
            WebElement rejectButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("gravitoLightCMP-layer1-reject-all")));
            rejectButton.click();
            System.out.println("Klikattiin 'Jatka välttämättömillä'");

            // klikataan eruojackpot kohasta
            // WebElement lotto = driver.findElement(
            // By.cssSelector("button.Button-module_content__4Nr7m"));
            // lotto.click();

            // löytyykö tuloksia tältä viikolta
            WebElement element = driver
                    .findElement(By.cssSelector("div.Notification-module_content__9OWys"));

            // TODO:
            // muuta TreeMapiksi --> pitää järjestyksen automaattisesti joten sitten on
            // helppo ottaa n määrä useiten tulleita numeroita tai yhtä montesti
            // esim list[6].getValue() ... loopilla jos on pienempi tai yhtäsuuri arvo
            // avaimella nii lisää listaan .. muuten break
            if (element.getText().equals("Ei tuloksia")) {
                HashMap<String, Integer> results1 = new HashMap<>();
                HashMap<String, Integer> results2 = new HashMap<>();
                HashMap<String, Integer> results3 = new HashMap<>();

                int count = 33;

                for (int i = 0; i < count; i++) {

                    WebElement prevWeekBtn = driver
                            .findElement(By.cssSelector(
                                    "button.NavButton-module_center__Z4M53"));
                    prevWeekBtn.click();

                    // ootetaan että varmasti numerot on ladattu
                    WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
                    wait2.until(ExpectedConditions.visibilityOfElementLocated(
                            By.cssSelector("div.DrawGameResults-module__drawGameResult--Tmlnv")));

                    // haetaan noi containerit jossa numerot on
                    List<WebElement> containers = driver.findElements(
                            By.cssSelector("div.DrawGameResults-module__drawGameResult--Tmlnv"));

                    for (WebElement container : containers) {
                        // List<WebElement> primary = container.findElements(
                        // By.cssSelector(
                        // "div.DrawGameResults-module__numberContainer--_6ZFH
                        // div.DrawGameResults-module__number--RSTO3"));
                        List<WebElement> primary = container.findElements(
                                By.cssSelector(
                                        "div.DrawGameResults-module__numberContainer--_6ZFH > div.DrawGameResults-module__number--RSTO3:not(.DrawGameResults-module__drawGameResult--lotto--secondary--eM52v):not(.DrawGameResults-module__drawGameResult--lotto--tertiary--kXV9C)"));

                        List<WebElement> secondary = container.findElements(
                                By.cssSelector(
                                        "div.DrawGameResults-module__drawGameResult--lotto--secondary--eM52v"));

                        List<WebElement> plus = container.findElements(
                                By.cssSelector(
                                        "div.DrawGameResults-module__drawGameResult--lotto--tertiary--kXV9C"));

                        for (WebElement num : primary) {
                            if (results1.containsKey(num.getText())) {
                                Integer newValue = results1.get(num.getText()) + 1;
                                results1.replace(num.getText(), newValue);
                            } else {
                                results1.put(num.getText(), 1);
                            }

                        }
                        for (WebElement second : secondary) {
                            if (results2.containsKey(second.getText())) {
                                Integer newValue = results2.get(second.getText()) + 1;
                                results2.replace(second.getText(), newValue);
                            } else {
                                results2.put(second.getText(), 1);
                            }
                        }
                        for (WebElement p : plus) {
                            if (results3.containsKey(p.getText())) {
                                Integer newValue = results3.get(p.getText()) + 1;
                                results3.replace(p.getText(), newValue);
                            } else {
                                results3.put(p.getText(), 1);
                            }
                        }

                    }
                }

                // System.out.println(results1.toString());
                // System.out.println(results2.toString());
                // System.out.println(results3.toString());

                // System.out.println(SortArray.sortHashMapPrimaryLotto(results1));
                // System.out.println(SortArray.sortHashMapSecondaryLotto(results2));
                // System.out.println(SortArray.sortHashMapPlusLotto(results3));

                List<String> prim = SortArray.sortHashMapPrimaryLotto(results1);
                List<String> sec = SortArray.sortHashMapSecondaryLotto(results2);
                List<String> plus = SortArray.sortHashMapPlusLotto(results3);

                List<String> result = new ArrayList<>();
                result.add("\n" + count + " viikon Lotto arvonnan useimmiten osuvat paanumerot:");
                result.addAll(prim);
                result.add("\n");
                result.add("Lisanumero(t):");
                result.addAll(sec);
                result.add("\n");
                result.add("Plusnumero(t):");
                result.addAll(plus);

                WriteToFile.writeListToFile(result, "Lotto_tulokset.txt");

            } else {
                System.out.println("***************");
            }

        } catch (

        Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit(); // sulkee selaimen lopuksi
        }
    }
}
