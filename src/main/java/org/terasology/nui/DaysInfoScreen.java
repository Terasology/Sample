/*
 * Copyright 2019 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.terasology.daysinfonui;

import org.terasology.rendering.nui.CoreScreenLayer;

import org.terasology.rendering.nui.CoreScreenLayer;
import org.terasology.rendering.nui.UIWidget;
import org.terasology.rendering.nui.widgets.UIButton;
import org.terasology.rendering.nui.widgets.UIText;
import java.util.Calendar;

public class DaysInfoScreen extends CoreScreenLayer {
    Calendar c = Calendar.getInstance();
    private UIText infoArea;
    private UIButton updateInfoButton;
    String day;

    private void findDay(){
        if(c.get(Calendar.DAY_OF_YEAR) == 	342){
            day = "WORLD TERASOLOGY DAY";
            //JANUARY
        }else if(c.get(Calendar.DAY_OF_YEAR) == 4){
            day = "World Braille Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 24){
            day = "International Day of Education";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 27){
            day = "International Day of Commemoration in Memory of the Victims of the Holocaust";
            //FEBRUARY
        }else if(c.get(Calendar.DAY_OF_YEAR) == 35){
            day = "World Cancer Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 37){
            day = "International Day of Zero Tolerance to Female Genital Mutilation";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 41){
            day = "World Pulses Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 42){
            day = "International Day of Women and Girls in Science";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 44){
            day = "World Radio Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 51){
            day = "World Day of Social Justice";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 52){
            day = "International Mother Language Day";
            //MARCH
        }else if(c.get(Calendar.DAY_OF_YEAR) == 60){
            day = "Zero Discrimination Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 62){
            day = "World Wildlife Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 67){
            day = "International Women's Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 70){
            day = "nailorcn's Birthday!";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 79){
            day = "International Day of Happiness";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 80){
            day = "World Poetry Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 81){
            day = "World Water Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 82){
            day = "World Meteorological Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 83){
            day = "World Tuberculosis Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 84){
            day = "International Day of Solidarity with Detained and Missing Staff Members";
            //APRIL
        }else if(c.get(Calendar.DAY_OF_YEAR) == 92){
            day = "World Autism Awareness Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 94){
            day = "International Day for Mine Awareness and Assistance in Mine Action";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 96){
            day = "International Day of Sport for Development and Peace";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 97){
            day = "World Health Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 102){
            day = "International Day of Human Space Flight";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 110){
            day = "International Day For Monuments and Sites";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 111){
            day = "World Creativity and Innovation Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 112){
            day = "Earth Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 113){
            day = "World Book and Copyright Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 114){
            day = "International Day of Multilateralism and Diplomacy for Peace";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 115){
            day = "International Delegate’s Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 116){
            day = "International Chernobyl Disaster Remembrance Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 118){
            day = "World Day for Safety and Health at Work";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 119){
            day = "International Dance Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 120){
            day = "International Jazz Day";
            //MAY
        }else if(c.get(Calendar.DAY_OF_YEAR) == 122){
            day = "World Tuna Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 123){
            day = "World Press Freedom Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 128){
            day = "ime of Remembrance and Reconciliation for Those Who Lost Their Lives During the Second World War";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 129){
            day = "ime of Remembrance and Reconciliation for Those Who Lost Their Lives During the Second World War";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 135){
            day = "International Day of Families";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 136){
            day = "International Day of Light";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 137){
            day = "World Telecommunication and Information Society Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 140){
            day = "World Bee Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 141){
            day = "World Day for Cultural Diversity for Dialogue and Development";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 142){
            day = "International Day for Biological Diversity";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 143){
            day = "International Day to End Obstetric Fistula";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 149){
            day = "International Day of UN Peacekeepers";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 151){
            day = "World No-Tobacco Day";
            //JUNE
        }else if(c.get(Calendar.DAY_OF_YEAR) == 152){
            day = "Global Day of Parents";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 154){
            day = "World Bicycle Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 155){
            day = "International Day of Innocent Children Victims of Aggression";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 156){
            day = "World Environment Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 158){
            day = "World Food Safety Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 159){
            day = "World Oceans Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 163){
            day = "World Day Against Child Labour";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 164){
            day = "International Albinism Awareness Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 165){
            day = "World Blood Donor Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 166){
            day = "World Elder Abuse Awareness Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 167){
            day = "International Day of Family Remittance";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 168){
            day = "World Day to Combat Desertification and Drought";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 169){
            day = "Sustainable Gastronomy Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 170){
            day = "International Day for the Elimination of Sexual Violence in Conflict";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 171){
            day = "World Refugee Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 172){
            day = "International Day of Yoga";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 174){
            day = "United Nations Public Service Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 176){
            day = "Day of the Seafarer";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 177){
            day = "International Day against Drug Abuse and Illicit Trafficking";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 178){
            day = "Micro-, Small and Medium-sized Enterprises Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 180){
            day = "International Day of the Tropics";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 181){
            day = "International Asteroid Day";
            //JULY
        }else if(c.get(Calendar.DAY_OF_YEAR) == 192){
            day = "World Population Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 196){
            day = "World Youth Skills Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 199){
            day = "Nelson Mandela International Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 209){
            day = "World Hepatitis Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 211){
            day = "International Day of Friendship";
            //AUGUST
        }else if(c.get(Calendar.DAY_OF_YEAR) == 221){
            day = "International Day of the World's Indigenous Peoples";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 224){
            day = "International Youth Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 231){
            day = "World Humanitarian Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 233){
            day = "International Day of Remembrance and Tribute to the Victims of Terrorism";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 235){
            day = "International Day for the Remembrance of the Slave Trade and Its Abolition";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 241){
            day = "International Day against Nuclear Tests";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 242){
            day = "International Day of the Victims of Enforced Disappearances";
            //SEPTEMBER
        }else if(c.get(Calendar.DAY_OF_YEAR) == 248){
            day = "International Day of Charity";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 251){
            day = "International Literacy Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 253){
            day = "World Suicide Prevention Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 255){
            day = "United Nations Day for South-South Cooperation";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 258){
            day = "International Day of Democracy";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 259){
            day = "International Day for the Preservation of the Ozone Layer";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 264){
            day = "International Day of Peace";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 266){
            day = "International Day of Sign Languages";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 269){
            day = "International Day for the Total Elimination of Nuclear Weapons";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 270){
            day = "World Tourism Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 271){
            day = "International Day for Universal Access to Information";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 273){
            day = "International Translation Day";
            //OCTOBER
        }else if(c.get(Calendar.DAY_OF_YEAR) == 274){
            day = "International Day of Older Persons";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 275){
            day = "International Day of Non-Violence";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 278){
            day = "World Teachers’ Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 282){
            day = "World Post Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 283){
            day = "World Mental Health Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 284){
            day = "International Day of the Girl Child";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 286){
            day = "International Day for Disaster Reduction";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 288){
            day = "Global Handwashing Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 289){
            day = "World Food Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 290){
            day = "International Day for the Eradication of Poverty";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 293){
            day = "World Statistics Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 297){
            day = "United Nations Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 300){
            day = "World Day for Audiovisual Heritage";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 304){
            day = "World Cities Day";
            //NOVEMBER
        }else if(c.get(Calendar.DAY_OF_YEAR) == 306){
            day = "International Day to End Impunity for Crimes against Journalists";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 309){
            day = "World Tsunami Awareness Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 310){
            day = "International Day for Preventing the Exploitation of the Environment in War and Armed Conflict";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 314){
            day = "World Science Day for Peace and Development";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 318){
            day = "World Diabetes Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 320){
            day = "International Day for Tolerance";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 323){
            day = "World Toilet Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 324){
            day = "Universal Children’s Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 325){
            day = "World Television Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 329){
            day = "International Day for the Elimination of Violence against Women";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 333){
            day = "International Day of Solidarity with the Palestinian People";
            //DECEMBER
        }else if(c.get(Calendar.DAY_OF_YEAR) == 335){
            day = "World AIDS Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 336){
            day = "International Day for the Abolition of Slavery";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 337){
            day = "International Day of Persons with Disabilities";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 339){
            day = "World Soil Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 341){
            day = "International Civil Aviation Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 343){
            day = "International Anti-Corruption Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 344){
            day = "Human Rights Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 345){
            day = "International Mountain Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 352){
            day = "International Migrants Day";
        }else if(c.get(Calendar.DAY_OF_YEAR) == 354){
            day = "International Human Solidarity Day";
        }else{
            day = "YOUR DAY!";
        }
    }

    private void setInfoAreaText(){
        c.setTimeInMillis(System.currentTimeMillis());
        String date = c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DAY_OF_MONTH);
        String time = c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND);
        findDay();
        infoArea.setText("Current Time: "+ date + " " + time + "\nToday is: " + day );
    }

    @Override
    public void initialise() {
        infoArea = find("infoArea", UIText.class);
        updateInfoButton = find("updateInfoButton", UIButton.class);
        setInfoAreaText();
        if (updateInfoButton != null) {
            updateInfoButton.subscribe((UIWidget button) -> setInfoAreaText());
        }
    }
}
