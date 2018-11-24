/*
 * Copyright 2018 MovingBlocks
 *
 * Licensed under the Apache Licence, Version 2.0 (the 'Licence');
 * you may not use this file except in compliance with the Licence.
 * You may obtain a copy of the Licence at
 *
 *	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an 'AS IS' BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Licence for the specific language governing permissions and
 * limitations under the Licence.
 */
package org.terasology.tutorial.nui;

import org.terasology.rendering.nui.CoreScreenLayer;
import org.terasology.rendering.nui.widgets.UIButton;
import org.terasology.rendering.nui.widgets.UIText;

public class StuffLengthsScreen extends CoreScreenLayer {

	private UIText infoArea;
	private UIButton updateInfoButton;

	private String[] infoItems = {"Quantum foam: 9.3e-36 m", "Quantum string: 9.3e-36 m", "Planck length: 1.6e-35 m", "Neutrino: 1e-24 m", "Top quark: 1e-22 m", "High-energy neutrino: 1.5e-20 m", "Bottom quark: 3e-20 m", "Charm quark: 1e-19 m", "Strange quark: 4e-19 m", "Down quark: 1e-18 m", "Up quark: 1e-18 m", "Range of weak force: 1e-17 m", "Scale of proton: 1e-15 m", "Scale of neutron: 1e-15 m", "Helium nucleus: 3e-15 m", "Electron (classical): 5.64e-15 m", "Chlorine nucleus: 6e-15 m", "Uranium nucleus: 1.5e-14 m", "Scale of gamma-ray wavelength: 1e-12 m", "Helium atom: 2.5e-11 m", "Hydrogen atom: 3.1e-11 m", "Smallest thing visible to electron microscope: 5e-11 m", "Ångström: 1e-10 m", "Water molecule: 2.8e-10 m", "Carbon atom: 3.4e-10 m", "Caesium atom: 5e-10 m", "Scale of X-ray wavelength: 5e-10 m", "Alpha helix: 8e-10 m", "Glucose molecule: 8e-10 m", "Buckyball: 1e-9 m", "Carbon nanotube: 1e-9 m", "Transistor gate: 1e-9 m", "Phospholipid: 2.5e-9 m", "DNA: 3e-9 m", "Phospholipid bilayer: 5e-9 m", "Porcine circovirus: 1.7e-8 m", "Hepatitis B virus: 4.2e-8 m", "Scale of ultraviolet wavelength: 6e-8 m", "HIV: 9e-8 m", "Bacteriophage: 2e-7 m", "Smallest thing visible to optical microscope: 2e-7 m", "Mamavirus: 3.9e-7 m", "Wavelength of violet light: 4e-7 m", "Megavirus: 4.4e-7 m", "Wavelength of red light: 7.5e-7 m", "Y chromosome: 1.5e-6 m", "Clay particle: 2e-6 m", "Escherichia coli: 2e-6 m", "Mitochondrion: 4e-6 m", "X chromosome: 4e-6 m", "Erythrocyte: 7e-6 m", "Human nucleus: 7e-6 m", "Chloroplast: 8e-6 m", "Leucocyte: 1e-5 m", "Scale of infrared wavelength: 1.5e-5 m", "Width of silk fibre: 1.5e-5 m", "Twip: 1.7639e-5 m", "Mist droplet: 2e-5 m", "Thou: 2.54e-5 m", "Skin cell: 3.5e-5 m", "Scale of silt particle: 5e-5 m", "Smallest object visible to naked eye: 1e-4 m", "Width of human hair: 1e-4 m", "Human ovum: 1.2e-4 m", "Typical thickness of paper: 1.5e-4 m", "Paramecium: 2e-4 m", "House dust mite: 3e-4 m", "LCD pixel: 3e-4 m", "Amoeba: 3.5e-4 m", "Salt grain: 5e-4 m", "Sand grain: 5e-4 m", "Scale of lead in mechanical pencil: 7e-4 m", "Thiomargarita namibiensis: 7.5e-4 m", "Duckweed: 2e-3 m", "Ant: 4e-3 m", "Length of rice grain: 5e-3 m", "Sleet: 5e-3 m", "Sunflower seed: 7e-3 m", "Coffee bean: 1e-2 m", "Scale of microwave wavelength: 1e-2 m", "Glass marble: 1.5e-2 m", "US penny: 1.905e-2 m", "Inch: 2.54e-2 m", "Quail egg: 3e-2 m", "Lumbricus terrestris: 4e-2 m", "Matchstick: 5e-2 m", "Chicken egg: 5.5e-2 m", "Hummingbird: 1e-1 m", "Shrew: 1e-1 m", "Approximate viewport size: 2e-1 m", "Largest hailstone: 2.032e-1 m", "Teapot: 2.159e-1 m", "NBA basketball: 2.3851e-1 m", "Inch ruler: 3.048e-1 m", "Beach ball: 8e-1 m", "Rafflesia arnoldii: 1e0 m", "Raphus cucullatus: 1e0 m", "Scale of FM-radio wavelength: 1e0 m", "Homo sapiens sapiens: 1.7e0 m", "Sunflower height: 2.5e0 m", "Macrocheira kaempferi: 3e0 m", "Megascolides australis: 3e0 m", "Wingspan record of Diomedea exulans: 3.7e0 m", "Elephant: 5e0 m", "Height of giraffe: 6e0 m", "Length of Tyrannosaurus rex: 7e0 m", "Apollo Lunar Module width: 9.4e0 m", "Carnegia gigantea: 1.4e1 m", "Average US house: 1.5e1 m", "Oak tree: 1.5e1 m", "Balaenoptera musculus: 3e1 m", "Argentinosaurus huinculensis: 3.97e1 m", "Boeing 747-8 length: 7.625e1 m", "ISS width: 1.085e2 m", "American-football field: 1.09728e2 m", "Saturn V: 1.106e2 m", "Hyperion (tree): 1.1592e2 m", "Great Pyramid of Giza: 1.467e2 m", "Washington Monument: 1.69294e2 m", "Gateway Arch: 1.92e2 m", "Hoover Dam height: 2.214e2 m", "RMS Titanic: 2.691e2 m", "Eiffel Tower: 3.24e2 m", "Half Dome prominence: 4.1e2 m", "Vatican City: 8e2 m", "Burj Khalifa tip: 8.298e2 m", "Angel Falls: 9.79e2 m", "Boeing Everett Factory: 1e3 m", "Scale of AM-radio wavelength: 1e3 m", "Uluru length: 3e3 m", "Central Park: 4e3 m", "3753 Cruithne: 5e3 m", "Palm Jebel Ali: 8e3 m", "Large Hadron Collider: 8.6e3 m", "Mount Everest: 8.85e3 m", "Depth of Mariana Trench: 1.0994 m", "1P/Halley: 1.1e4 m", "Mars II: 1.5e4 m", "Neutron star: 2.4e4 m", "Mars I: 2.7e4 m", "Marathon: 4.2194988e4 m", "Nix: 5.3e4 m", "Hydra: 6.5e4 m", "Rhode Island: 7.5e4 m", "Brunei: 1.2e5 m", "Rwanda: 2.4e5 m", "West Virginia: 4e5 m", "Grand Canyon: 4.46e5 m", "(136199) Eris I Dysnomia: 7e5 m", "1 Ceres: 9.652e5 m", "90377 Sedna: 1e6 m", "Italy: 1.1e6 m", "50000 Quaoar: 1.11e6 m", "California: 1.2e6 m", "Texas: 1.2e6 m", "Charon: 1.212e6 m", "136199 Eris: 2.326e6 m", "134340 Pluto: 2.3766e6 m", "Great Barrier Reef: 2.575e6 m", "Triton: 2.7068e6 m", "Europa: 3.1216e6 m", "Moon: 3.4742e6 m", "Io: 3.66e6 m", "United States of America: 4.2e6 m", "Callisto: 4.8206e6 m", "Mercury: 4.8794e6 m", "Titan: 5.14946e6 m", "Ganymede: 5.2682e6 m", "Mars: 6.779e6 m", "Asia: 8e6 m", "Sirius B: 1.17e7 m", "Venus: 1.21036e7 m", "Earth: 1.2742018e7 m", "Size of Great Wall of China: 2.1196e7 m", "Neptune: 4.9244e7 m", "Uranus: 5.0724e7 m", "Minecraft world: 6.4e7 m", "Gliese 229B: 6.54e7 m", "Saturn: 1.16464e8 m", "Jupiter: 1.39822e8 m", "Proxima Centauri: 2.15e8 m", "Wolf 359: 2.23e8 m", "TrES-4b: 2.57e8 m", "Earth–Moon distance: 3.844e8 m", "Kapteyn's Star: 4.05e8 m", "Luyten's Star: 4.87e8 m", "Gliese 229A: 9.6e8 m", "Alpha Centauri B: 1.201e9 m", "Sun: 1.3914e9 m", "Alpha Centauri A: 1.702e9 m", "Sirius A: 2.38e9 m", "Altair: 2.55e9 m", "Procyon A: 2.85e9 m", "Vega: 3.29e9 m", "Regulus A: 4.3e9 m", "Spica: 1.04e10 m", "Pollux: 1.22e10 m", "Capella: 1.67e10 m", "Alnitak Aa: 2.78e10 m", "Arcturus: 3.53e10 m", "Polaris Aa: 5.22e10 m", "Aldebaran: 6.14e10 m", "Albireo Aa: 9.6e10 m", "Gamma Crucis: 1.17e11 m", "Rigel A (rough): 1.4e11 m", "Astronomical unit: 1.4959787087e11", "Epsilon Pegasi: 2.57e11 m", "Deneb: 2.82e11 m", "V354 Cephei (rough): 3.08e11 m", "Pistol Star: 4.26e11 m", "R Doradus: 5.15e11 m", "S Doradus: 5.29e11 m", "La Superba (rough): 5.43e11 m", "Antares (rough): 1.03e12 m", "Betelgeuse: 1.23e12 m", "Mu Cephei: 1.75e12 m", "VV Cephei A: 1.95e12 m", "KY Cygni: 1.98e12 m", "WOH G64: 2.27e11 m", "UY Scuti: 2.38e12 m", "Kuiper Belt: 1.5e13 m", "Homunculus Nebula: 2e13 m", "2018-06-04 distance of Voyager 1 from Earth: 2.1289e13 m", "Light-day: 2.59020683712e13 m", "C/1995 O1 aphelion: 5.55e13 m", "Sedna aphelion: 1.4e14 m", "Stingray Nebula: 8e14 m", "Distance from Proxima Centauri to Alpha Centauri A: 1.42e15 m", "Cat's Eye Nebula: 2.37e15 m", "Gomez's Hamburger: 2.37e15 m", "Hourglass Nebula: 2.84e15 m", "Blinking Nebula: 4.26e15 m", "Light-year: 9.4607304725808e15 m", "Rotten Egg Nebula: 1.32e16 m", "Ring Nebula: 1.61e16 m", "Ant Nebula: 1.89e16 m", "Eskimo Nebula: 1.89e16 m", "Horsehead Nebula: 1.89e16 m", "Oort Cloud: 1.89e16 m", "Boomerang Nebula: 1.99e16 m", "Helix Nebula: 2.84e16 m", "Parsec: 3.08568e16 m", "Distance from sun to Proxima Centauri: 4.01e16 m", "Bubble Nebula: 6.62e16 m", "Cone Nebula: 7.57e16 m", "Pillars of Creation: 9.46e16 m", "Crab Nebula: 1.04e17 m", "Spire: 1.89e17 m", "Orion Nebula: 2.27e17 m", "North American Nebula: 3.78e17 m", "Great Nebula in Carina: 5.68e17 m", "Cave Nebula: 6.62e17 m", "Eagle Nebula: 6.62e17 m", "Rosette Nebula: 9.46e17 m", "Lagoon Nebula: 1.04e18 m", "Omega Centauri: 1.42e18 m", "Barnard's Loop: 2.84e18 m", "Messier 54: 2.84e18 m", "Tarantula Nebula: 5.68e18 m", "Leo II: 1.89e19 m", "Canis Major Overdensity: 4.73e19 m", "Canes Venatici I: 6.15e19 m", "Small Magellanic Cloud: 6.62e19 m", "Sagittarius Dwarf Spheroidal Galaxy: 9.46e19 m", "Large Magellanic Cloud: 1.32e20 m", "NGC 3310: 2.08e20 m", "Triangulum Galaxy: 4.73e20 m", "Sombrero Galaxy: 4.73e20 m", "Milky Way: 1.14e21 m", "Andromeda Galaxy: 1.42e21 m", "Cartwheel Galaxy: 1.42e21 m", "Pinwheel Galaxy: 1.61e21 m", "Whirlpool Galaxy: 1.7e21 m", "NGC 1232: 2.08e21 m", "Virgo A: 2.37e21 m", "Tadpole Galaxy: 2.84e21 m", "Distance Earth has travelled relative to sun: 4.26e21 m", "NGC 4889: 4.73e21 m", "Distance to Andromeda Galaxy: 1.89e22 m", "IC 1101: 4.73e22 m", "Abell 2029: 5.68e22 m", "Local Group: 9.46e22 m", "Fornax Cluster: 1.89e23 m", "Virgo Cluster: 2.84e23 m", "Virgo Supercluster: 1.04e24 m", "Distance to Great Attractor: 2.37e24 m", "CMB cold spot: 4.73e24 m", "Distance to Shapley Supercluster: 6.15e24 m", "Pisces–Cetus Supercluster Complex: 9.46e24 m", "Sloan Great Wall: 1.31e25 m", "Gigaparsec: 3.08568e25 m", "Hercules–Corona Borealis Great Wall: 9.46e25 m", "Distance to HDF: 1.21e26 m", "Diameter of observable universe: 8.8e26 m"};
	private int infoIndex = 0;

	@Override
	public void initialise() {
		infoArea = find("infoArea", UIText.class);
		updateInfoButton = find("updateInfoButton", UIButton.class);

		if (updateInfoButton != null) {
			updateInfoButton.subscribe(button -> {
				infoArea.setText(infoItems[infoIndex]);
				if (infoIndex > infoItems.length() - 2)
					infoIndex = 0;
				else infoIndex++;
			});
		}
	}
}