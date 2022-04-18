package basico;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import supporte.VerificarElemento;

import static tests.IniciarPorto.ARQRESUMO;


public class AbaQuestionario {

    String tipoQuest;

    public void preencherAbaQuestionario(WebDriver driver, Variaveis dados) throws InterruptedException {

        if (!ARQRESUMO.getTipoCalculo().toUpperCase().contains("TAXI")) {
            if (VerificarElemento.verificar(driver, "formquestionario:btnsalvarContinuarQuestionario", "ID", 30)) {
                System.out.println("inciar o preenchimento da tela questionarios");
                Thread.sleep(1000);


                if (VerificarElemento.verificar(driver, "formquestionario:btnPopularDadosProponente", "ID", 30)) {
                    driver.findElement(By.id("formquestionario:btnPopularDadosProponente")).click();
                }


                if (!dados.getRelacaoseg().toUpperCase().contains("PROPRIO")) {

                    driver.findElement(By.id("formquestionario:txt_qCode130")).clear();
                    driver.findElement(By.id("formquestionario:txt_qCode130")).sendKeys(dados.getCpfcond());
                    driver.findElement(By.id("formquestionario:txt_qCode130")).sendKeys(new CharSequence[]{Keys.TAB});
                    Thread.sleep(1000);
                    System.out.println("preencheu o cpf");

                    driver.findElement(By.id("formquestionario:txt_qCode129")).clear();
                    driver.findElement(By.id("formquestionario:txt_qCode129")).sendKeys(dados.getNomeCond());
                    driver.findElement(By.id("formquestionario:txt_qCode129")).sendKeys(new CharSequence[]{Keys.TAB});
                    Thread.sleep(1000);
                    System.out.println("preencheu o nome");
                }

                if (VerificarElemento.verificar(driver, "formquestionario:sel_qCode91", "ID", 1)) {
                    System.out.println("achou campo sexo");
                    Select drpSexo = new Select(driver.findElement(By.id("formquestionario:sel_qCode91")));
                    if (dados.getAlienado().toUpperCase().contains("F")) {
                        drpSexo.selectByIndex(2);
                    } else {
                        drpSexo.selectByIndex(1);
                    }

                    Thread.sleep(1000);
                }

                if (VerificarElemento.verificar(driver, "formquestionario:sel_qCode92", "ID", 1)) {
                    System.out.println("achou campo estado civil");
                    Select drpEstCivil = new Select(driver.findElement(By.id("formquestionario:sel_qCode92")));
                    if (dados.getEstadoCivil().toUpperCase().contains("CA")) {
                        drpEstCivil.selectByIndex(2);
                    } else if (dados.getEstadoCivil().toUpperCase().contains("SO")) {
                        drpEstCivil.selectByIndex(1);
                    } else if (dados.getEstadoCivil().toUpperCase().contains("DI") || (dados.getEstadoCivil().toUpperCase().contains("SE"))) {
                        drpEstCivil.selectByIndex(4);
                    } else if (dados.getEstadoCivil().toUpperCase().contains("VI")) {
                        drpEstCivil.selectByIndex(3);
                    } else {
                        drpEstCivil.selectByIndex(2);
                    }
                    Thread.sleep(1000);
                }


                if (VerificarElemento.verificar(driver, "formquestionario:txt_qCode2", "ID", 1)) {
                    System.out.println("achou data");
                    driver.findElement(By.id("formquestionario:txt_qCode2")).clear();
                    for (int i = 0; i < dados.getDatacond().length(); ) {
                        char c = dados.getDatacond().charAt(i);
                        String s = new StringBuilder().append(c).toString();
                        driver.findElement(By.id("formquestionario:txt_qCode2")).sendKeys(s);
                        i++;
                    }

                    driver.findElement(By.id("formquestionario:txt_qCode2")).sendKeys(new CharSequence[]{Keys.TAB});
                    Thread.sleep(2000);
                    System.out.println("preencheu a data");
                }

                if (driver.findElement(By.xpath("//*[@id=\"formquestionario\"]/div[2]/div[1]/h3")).isDisplayed()) {
                    System.out.println(driver.findElement(By.xpath("//*[@id=\"formquestionario\"]/div[2]/div[1]/h3")).getText());
                    tipoQuest = driver.findElement(By.xpath("//*[@id=\"formquestionario\"]/div[2]/div[1]/h3")).getText();
                }

                if (!tipoQuest.contains("Auto Jovem")) {

                    if (VerificarElemento.verificar(driver, "formquestionario:label_qCode134", "ID", 1)) {
                        System.out.println("achou resid25");
                        WebElement chkDirige25Nao = driver.findElement(By.id("formquestionario:table_qCode134:0:chk_rCode265"));
                        WebElement chkDirige25Sim = driver.findElement(By.id("formquestionario:table_qCode134:0:label_rCode267"));
                        WebElement chkDirige25NInfo = driver.findElement(By.id("formquestionario:table_qCode134:0:chk_rCode1"));
                        if (chkDirige25NInfo.isSelected()) {
                            chkDirige25NInfo.click();
                            if (dados.getDirige25().toUpperCase().contains("SIM")) {
                                chkDirige25Sim.click();
                            } else {
                                chkDirige25Nao.click();
                            }
                        } else {
                            if (chkDirige25Nao.isSelected() && dados.getDirige25().toUpperCase().contains("SIM")) {
                                chkDirige25Nao.click();
                                Thread.sleep(1000);
                                chkDirige25Sim.click();
                                System.out.println("clicou em sim");
                            } else if (!chkDirige25Nao.isSelected() && dados.getDirige25().toUpperCase().contains("NAO")) {
                                chkDirige25Sim.click();
                                Thread.sleep(1000);
                                chkDirige25Nao.click();
                                System.out.println("clicou em nao");
                            }
                        }
                        if (dados.getDirige25().toUpperCase().contains("SIM")) {
                            Thread.sleep(1000);
                            System.out.println("clicou em sim");
                            if (dados.getSexo25().toUpperCase().contains("F")) {
                                driver.findElement(By.id("formquestionario:table_qCode153:0:chk_rCode10")).click();
                            } else if (dados.getSexo25().toUpperCase().contains("M")) {
                                driver.findElement(By.id("formquestionario:table_qCode153:0:chk_rCode9")).click();
                            } else {
                                driver.findElement(By.id("formquestionario:table_qCode153:0:chk_rCode362")).click();
                            }
                        }
                        Thread.sleep(1000);
                    }


                    System.out.println("cep");
                    if (VerificarElemento.verificar(driver, "formquestionario:link_proponenteqCode96", "ID", 1)) {
                        driver.findElement(By.id("formquestionario:link_proponenteqCode96")).click();
                    }
                    Thread.sleep(1000);

                    if (!tipoQuest.contains("Tradicional II")) {
                        if (VerificarElemento.verificar(driver, "formquestionario:sel_qCode135", "ID", 1)) {
                            System.out.println("achou tipo resid");
                            Select drpTiporesid = new Select(driver.findElement(By.id("formquestionario:sel_qCode135")));
                            if (dados.getTipoRes().toUpperCase().contains("CASA")) {
                                drpTiporesid.selectByIndex(1);
                            } else if (dados.getTipoRes().toUpperCase().contains("CASA COND")) {
                                drpTiporesid.selectByIndex(2);
                            } else if (dados.getTipoRes().toUpperCase().contains("APART") || (dados.getTipoRes().toUpperCase().contains("APTO"))) {
                                drpTiporesid.selectByIndex(3);
                            } else if (dados.getTipoRes().toUpperCase().contains("CHACARA")) {
                                drpTiporesid.selectByIndex(5);
                            } else {
                                drpTiporesid.selectByIndex(3);
                            }
                            Thread.sleep(1000);
                        }

                        if (VerificarElemento.verificar(driver, "formquestionario:chk_rCode1", "ID", 1)) {
                            WebElement chkGrg = driver.findElement(By.id("formquestionario:chk_rCode1"));
                            if (driver.findElement(By.id("formquestionario:chk_rCode1")).isSelected()) {
                                chkGrg.click();
                            }
                        }

                        if (VerificarElemento.verificar(driver, "formquestionario:sel_qCode135", "ID", 1)) {
                            System.out.println("achou grgres");
                            Select drpGrgRes = new Select(driver.findElement(By.id("formquestionario:sel_qCode150")));
                            if ((dados.getGrgRes().toUpperCase().contains("SIM")) && (dados.getPortAut().toUpperCase().contains("NAO"))) {
                                drpGrgRes.selectByIndex(1);
                            } else if ((dados.getGrgRes().toUpperCase().contains("SIM")) && (dados.getPortAut().toUpperCase().contains("SIM"))) {
                                drpGrgRes.selectByIndex(2);
                            } else if (dados.getGrgRes().toUpperCase().contains("NAO")) {
                                drpGrgRes.selectByIndex(3);
                            }
                            Thread.sleep(1000);
                        }

                        if (VerificarElemento.verificar(driver, "formquestionario:sel_qCode151", "ID", 1)) {
                            System.out.println("achou grgtrab");
                            Select drpGrgTrab = new Select(driver.findElement(By.id("formquestionario:sel_qCode151")));
                            if (dados.getGrgTrab().toUpperCase().contains("SIM")) {
                                drpGrgTrab.selectByIndex(1);
                            } else if (dados.getGrgTrab().toUpperCase().contains("UTILIZA")) {
                                drpGrgTrab.selectByIndex(3);
                            } else if (dados.getGrgTrab().toUpperCase().contains("NAO")) {
                                drpGrgTrab.selectByIndex(2);
                            }
                            Thread.sleep(1000);
                        }

                        if (VerificarElemento.verificar(driver, "formquestionario:sel_qCode152", "ID", 1)) {
                            System.out.println("achou grgtrab");
                            Select drpGrgEsc = new Select(driver.findElement(By.id("formquestionario:sel_qCode152")));
                            if (dados.getGrgEscola().toUpperCase().contains("SIM")) {
                                drpGrgEsc.selectByIndex(1);
                            } else if (dados.getGrgEscola().toUpperCase().contains("UTILIZA")) {
                                drpGrgEsc.selectByIndex(3);
                            } else if (dados.getGrgEscola().toUpperCase().contains("NAO")) {
                                drpGrgEsc.selectByIndex(2);
                            }
                            Thread.sleep(1000);
                        }


                    }
                    if (tipoQuest.contains(" Versão 24")) {
                        if (VerificarElemento.verificar(driver, "formquestionario:label_qCode97")) {
                            System.out.println("achou tipo comercial");
                            WebElement chkComercialNInfo = driver.findElement(By.id("formquestionario:table_qCode97:0:chk_rCode1"));
                            WebElement chkComercialSim = driver.findElement(By.id("formquestionario:table_qCode97:0:chk_rCode5"));
                            WebElement chkComercialNao = driver.findElement(By.id("formquestionario:table_qCode97:0:chk_rCode6"));
                            if (chkComercialNInfo.isSelected()) {
                                chkComercialNInfo.click();
                            } else if (chkComercialSim.isSelected()) {
                                chkComercialSim.click();
                            } else {
                                chkComercialNao.click();
                            }

                            if (dados.getComercial().toUpperCase().contains("SIM")) {
                                chkComercialSim.click();
                            } else {
                                chkComercialNao.click();
                            }
                            Thread.sleep(1000);
                        }
                    }

                    if (VerificarElemento.verificar(driver, "formquestionario:sel_qCode98", "ID", 1)) {
                        System.out.println("achou dispositivo");
                        Select drpDisp = new Select(driver.findElement(By.id("formquestionario:sel_qCode98")));
                        drpDisp.selectByIndex(4);
                        Thread.sleep(1000);
                    }

                }
            }
        }
        if (VerificarElemento.verificar(driver, "formquestionario:btnsalvarContinuarQuestionario", "ID", 30)) {
            driver.findElement(By.id("formquestionario:btnsalvarContinuarQuestionario")).click();
        }
    }
}
