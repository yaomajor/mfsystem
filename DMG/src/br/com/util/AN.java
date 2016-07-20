package br.com.util;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AN {
	public static ResultSet rs = null;
	public static Statement stmt = null;
	public static PreparedStatement stmtP = null;
	
	public static Connection oConn = null;
	public static String doublePStringRS(double valor) {
		String ret = "0";
		DecimalFormat d = new DecimalFormat("#,###.00");
		ret = d.format(valor);
		return ret;
	}
	public static double stringPDouble(String val) {
		double ret = 0;
		
		val = val.equals("")?"0":val;
		try {
			val = val.replace(".", "");
		} catch (Exception e) {
		}
		try {
			val = val.replace(",", ".");
		} catch (Exception e) {
		}
		
	
		ret = Double.parseDouble(val);
		
//		try {
//			ret = Double.parseDouble(val);
//		} catch (Exception e) {
//		}
		
		return ret;
	}
	public static String retAteTraco(String a) {
		String ret = "0";
		int x = a.indexOf('-');
		if (x > 0) {
			x = x - 1;
			try {
				a = a.substring(0, x);
			} catch (Exception e) {
			}
			ret = a;
		}
		return ret;
	}
	
	public static String oitoDigitos(String num) {
		String ret = "";
		try {
			if (num.length() == 1) {
				ret = "0000000" + num;
			} else if (num.length() == 2) {
				ret = "000000" + num;
			} else if (num.length() == 3) {
				ret = "00000" + num;
			} else if (num.length() == 4) {
				ret = "0000" + num;
			} else if (num.length() == 5) {
				ret = "000" + num;
			} else if (num.length() == 6) {
				ret = "00" + num;
			} else if (num.length() == 7) {
				ret = "0" + num;
			} else {
				ret = num;
			}
		} catch (Exception e) {
		}
		return ret;

	}
	public static String[] retCFOPsCompraDentro(int tipo) {
		String[] ret = null;
		if (tipo == 1) {
			ret = new String[128];
			ret[0] = "1100";
			ret[1] = "1101";
			ret[2] = "1102";
			ret[3] = "1111";
			ret[4] = "1113";
			ret[5] = "1116";
			ret[6] = "1117";
			ret[7] = "1118";
			ret[8] = "1120";
			ret[9] = "1121";
			ret[10] = "1122";
			ret[11] = "1124";
			ret[12] = "1125";
			ret[13] = "1126";
			ret[14] = "1150";
			ret[15] = "1151";
			ret[16] = "1152";
			ret[17] = "1153";
			ret[18] = "1154";
			ret[19] = "1200";
			ret[20] = "1201";
			ret[21] = "1202";
			ret[22] = "1203";
			ret[23] = "1204";
			ret[24] = "1205";
			ret[25] = "1206";
			ret[26] = "1207";
			ret[27] = "1208";
			ret[28] = "1209";
			ret[29] = "1250";
			ret[30] = "1251";
			ret[31] = "1252";
			ret[32] = "1253";
			ret[33] = "1254";
			ret[34] = "1255";
			ret[35] = "1256";
			ret[36] = "1257";
			ret[37] = "1300";
			ret[38] = "1301";
			ret[39] = "1302";
			ret[40] = "1303";
			ret[41] = "1304";
			ret[42] = "1305";
			ret[43] = "1306";
			ret[44] = "1350";
			ret[45] = "1351";
			ret[46] = "1352";
			ret[47] = "1353";
			ret[48] = "1354";
			ret[49] = "1355";
			ret[50] = "1356";
			ret[51] = "1360";
			ret[52] = "1400";
			ret[53] = "1401";
			ret[54] = "1403";
			ret[55] = "1406";
			ret[56] = "1407";
			ret[57] = "1408";
			ret[58] = "1409";
			ret[59] = "1410";
			ret[60] = "1411";
			ret[61] = "1414";
			ret[62] = "1415";
			ret[63] = "1450";
			ret[64] = "1451";
			ret[65] = "1452";
			ret[66] = "1500";
			ret[67] = "1501";
			ret[68] = "1503";
			ret[69] = "1504";
			ret[70] = "1505";
			ret[71] = "1506";
			ret[72] = "1550";
			ret[73] = "1551";
			ret[74] = "1552";
			ret[75] = "1553";
			ret[76] = "1554";
			ret[77] = "1555";
			ret[78] = "1556";
			ret[79] = "1557";
			ret[80] = "1600";
			ret[81] = "1601";
			ret[82] = "1602";
			ret[83] = "1603";
			ret[84] = "1604";
			ret[85] = "1605";
			ret[86] = "1650";
			ret[87] = "1651";
			ret[88] = "1652";
			ret[89] = "1653";
			ret[90] = "1658";
			ret[91] = "1659";
			ret[92] = "1660";
			ret[93] = "1661";
			ret[94] = "1662";
			ret[95] = "1663";
			ret[96] = "1664";
			ret[97] = "1900";
			ret[98] = "1901";
			ret[99] = "1902";
			ret[100] = "1903";
			ret[101] = "1904";
			ret[102] = "1905";
			ret[103] = "1906";
			ret[104] = "1907";
			ret[105] = "1908";
			ret[106] = "1909";
			ret[107] = "1910";
			ret[108] = "1911";
			ret[109] = "1912";
			ret[110] = "1913";
			ret[111] = "1914";
			ret[112] = "1915";
			ret[113] = "1916";
			ret[114] = "1917";
			ret[115] = "1918";
			ret[116] = "1919";
			ret[117] = "1920";
			ret[118] = "1921";
			ret[119] = "1922";
			ret[120] = "1923";
			ret[121] = "1924";
			ret[122] = "1925";
			ret[123] = "1926";
			ret[124] = "1931";
			ret[125] = "1932";
			ret[126] = "1933";
			ret[127] = "1949";
		} else if (tipo == 2) {
			ret = new String[128];
			ret[0] = "2100";
			ret[1] = "2101";
			ret[2] = "2102";
			ret[3] = "2111";
			ret[4] = "2113";
			ret[5] = "2116";
			ret[6] = "2117";
			ret[7] = "2118";
			ret[8] = "2120";
			ret[9] = "2121";
			ret[10] = "2122";
			ret[11] = "2124";
			ret[12] = "2125";
			ret[13] = "2126";
			ret[14] = "2150";
			ret[15] = "2151";
			ret[16] = "2152";
			ret[17] = "2153";
			ret[18] = "2154";
			ret[19] = "2200";
			ret[20] = "2201";
			ret[21] = "2202";
			ret[22] = "2203";
			ret[23] = "2204";
			ret[24] = "2205";
			ret[25] = "2206";
			ret[26] = "2207";
			ret[27] = "2208";
			ret[28] = "2209";
			ret[29] = "2250";
			ret[30] = "2251";
			ret[31] = "2252";
			ret[32] = "2253";
			ret[33] = "2254";
			ret[34] = "2255";
			ret[35] = "2256";
			ret[36] = "2257";
			ret[37] = "2300";
			ret[38] = "2301";
			ret[39] = "2302";
			ret[40] = "2303";
			ret[41] = "2304";
			ret[42] = "2305";
			ret[43] = "2306";
			ret[44] = "2350";
			ret[45] = "2351";
			ret[46] = "2352";
			ret[47] = "2353";
			ret[48] = "2354";
			ret[49] = "2355";
			ret[50] = "2356";
			ret[51] = "2360";
			ret[52] = "2400";
			ret[53] = "2401";
			ret[54] = "2403";
			ret[55] = "2406";
			ret[56] = "2407";
			ret[57] = "2408";
			ret[58] = "2409";
			ret[59] = "2410";
			ret[60] = "2411";
			ret[61] = "2414";
			ret[62] = "2415";
			ret[63] = "2450";
			ret[64] = "2451";
			ret[65] = "2452";
			ret[66] = "2500";
			ret[67] = "2501";
			ret[68] = "2503";
			ret[69] = "2504";
			ret[70] = "2505";
			ret[71] = "2506";
			ret[72] = "2550";
			ret[73] = "2551";
			ret[74] = "2552";
			ret[75] = "2553";
			ret[76] = "2554";
			ret[77] = "2555";
			ret[78] = "2556";
			ret[79] = "2557";
			ret[80] = "2600";
			ret[81] = "2601";
			ret[82] = "2602";
			ret[83] = "2603";
			ret[84] = "2604";
			ret[85] = "2605";
			ret[86] = "2650";
			ret[87] = "2651";
			ret[88] = "2652";
			ret[89] = "2653";
			ret[90] = "2658";
			ret[91] = "2659";
			ret[92] = "2660";
			ret[93] = "2661";
			ret[94] = "2662";
			ret[95] = "2663";
			ret[96] = "2664";
			ret[97] = "2900";
			ret[98] = "2901";
			ret[99] = "2902";
			ret[100] = "2903";
			ret[101] = "2904";
			ret[102] = "2905";
			ret[103] = "2906";
			ret[104] = "2907";
			ret[105] = "2908";
			ret[106] = "2909";
			ret[107] = "2910";
			ret[108] = "2911";
			ret[109] = "2912";
			ret[110] = "2913";
			ret[111] = "2914";
			ret[112] = "2915";
			ret[113] = "2916";
			ret[114] = "2917";
			ret[115] = "2918";
			ret[116] = "2919";
			ret[117] = "2920";
			ret[118] = "2921";
			ret[119] = "2922";
			ret[120] = "2923";
			ret[121] = "2924";
			ret[122] = "2925";
			ret[123] = "2926";
			ret[124] = "2931";
			ret[125] = "2932";
			ret[126] = "2933";
			ret[127] = "2949";
		} else if (tipo == 3) {
			ret = new String[36];
			ret[0] = "3100";
			ret[1] = "3101";
			ret[2] = "3102";
			ret[3] = "3126";
			ret[4] = "3127";
			ret[5] = "3200";
			ret[6] = "3201";
			ret[7] = "3202";
			ret[8] = "3205";
			ret[9] = "3206";
			ret[10] = "3207";
			ret[11] = "3211";
			ret[12] = "3250";
			ret[13] = "3251";
			ret[14] = "3250";
			ret[15] = "3301";
			ret[16] = "3350";
			ret[17] = "3351";
			ret[18] = "3352";
			ret[19] = "3353";
			ret[20] = "3354";
			ret[21] = "3355";
			ret[22] = "3356";
			ret[23] = "3500";
			ret[24] = "3503";
			ret[25] = "3550";
			ret[26] = "3551";
			ret[27] = "3553";
			ret[28] = "3556";
			ret[29] = "3650";
			ret[30] = "3651";
			ret[31] = "3652";
			ret[32] = "3653";
			ret[33] = "3900";
			ret[34] = "3930";
			ret[35] = "3949";
		} else if (tipo == 4) {
			ret = new String[128];
			ret[0] = "5100";
			ret[1] = "5101";
			ret[2] = "5102";
			ret[3] = "5111";
			ret[4] = "5113";
			ret[5] = "5116";
			ret[6] = "5117";
			ret[7] = "5118";
			ret[8] = "5120";
			ret[9] = "5121";
			ret[10] = "5122";
			ret[11] = "5124";
			ret[12] = "5125";
			ret[13] = "5126";
			ret[14] = "5150";
			ret[15] = "5151";
			ret[16] = "5152";
			ret[17] = "5153";
			ret[18] = "5154";
			ret[19] = "5200";
			ret[20] = "5201";
			ret[21] = "5202";
			ret[22] = "5203";
			ret[23] = "5204";
			ret[24] = "5205";
			ret[25] = "5206";
			ret[26] = "5207";
			ret[27] = "5208";
			ret[28] = "5209";
			ret[29] = "5250";
			ret[30] = "5251";
			ret[31] = "5252";
			ret[32] = "5253";
			ret[33] = "5254";
			ret[34] = "5255";
			ret[35] = "5256";
			ret[36] = "5257";
			ret[37] = "5300";
			ret[38] = "5301";
			ret[39] = "5302";
			ret[40] = "5303";
			ret[41] = "5304";
			ret[42] = "5305";
			ret[43] = "5306";
			ret[44] = "5350";
			ret[45] = "5351";
			ret[46] = "5352";
			ret[47] = "5353";
			ret[48] = "5354";
			ret[49] = "5355";
			ret[50] = "5356";
			ret[51] = "5360";
			ret[52] = "5400";
			ret[53] = "5401";
			ret[54] = "5403";
			ret[55] = "5406";
			ret[56] = "5407";
			ret[57] = "5408";
			ret[58] = "5409";
			ret[59] = "5410";
			ret[60] = "5411";
			ret[61] = "5414";
			ret[62] = "5415";
			ret[63] = "5450";
			ret[64] = "5451";
			ret[65] = "5452";
			ret[66] = "5500";
			ret[67] = "5501";
			ret[68] = "5503";
			ret[69] = "5504";
			ret[70] = "5505";
			ret[71] = "5506";
			ret[72] = "5550";
			ret[73] = "5551";
			ret[74] = "5552";
			ret[75] = "5553";
			ret[76] = "5554";
			ret[77] = "5555";
			ret[78] = "5556";
			ret[79] = "5557";
			ret[80] = "5600";
			ret[81] = "5601";
			ret[82] = "5602";
			ret[83] = "5603";
			ret[84] = "5604";
			ret[85] = "5605";
			ret[86] = "5650";
			ret[87] = "5651";
			ret[88] = "5652";
			ret[89] = "5653";
			ret[90] = "5658";
			ret[91] = "5659";
			ret[92] = "5660";
			ret[93] = "5661";
			ret[94] = "5662";
			ret[95] = "5663";
			ret[96] = "5664";
			ret[97] = "5900";
			ret[98] = "5901";
			ret[99] = "5902";
			ret[100] = "5903";
			ret[101] = "5904";
			ret[102] = "5905";
			ret[103] = "5906";
			ret[104] = "5907";
			ret[105] = "5908";
			ret[106] = "5909";
			ret[107] = "5910";
			ret[108] = "5911";
			ret[109] = "5912";
			ret[110] = "5913";
			ret[111] = "5914";
			ret[112] = "5915";
			ret[113] = "5916";
			ret[114] = "5917";
			ret[115] = "5918";
			ret[116] = "5919";
			ret[117] = "5920";
			ret[118] = "5921";
			ret[119] = "5922";
			ret[120] = "5923";
			ret[121] = "5924";
			ret[122] = "5925";
			ret[123] = "5926";
			ret[124] = "5931";
			ret[125] = "5932";
			ret[126] = "5933";
			ret[127] = "5949";
		} else if (tipo == 6) {
			ret = new String[128];
			ret[0] = "6100";
			ret[1] = "6101";
			ret[2] = "6102";
			ret[3] = "6111";
			ret[4] = "6113";
			ret[5] = "6116";
			ret[6] = "6117";
			ret[7] = "6118";
			ret[8] = "6120";
			ret[9] = "6121";
			ret[10] = "6122";
			ret[11] = "6124";
			ret[12] = "6125";
			ret[13] = "6126";
			ret[14] = "6150";
			ret[15] = "6151";
			ret[16] = "6152";
			ret[17] = "6153";
			ret[18] = "6154";
			ret[19] = "6200";
			ret[20] = "6201";
			ret[21] = "6202";
			ret[22] = "6203";
			ret[23] = "6204";
			ret[24] = "6205";
			ret[25] = "6206";
			ret[26] = "6207";
			ret[27] = "6208";
			ret[28] = "6209";
			ret[29] = "6250";
			ret[30] = "6251";
			ret[31] = "6252";
			ret[32] = "6253";
			ret[33] = "6254";
			ret[34] = "6255";
			ret[35] = "6256";
			ret[36] = "6257";
			ret[37] = "6300";
			ret[38] = "6301";
			ret[39] = "6302";
			ret[40] = "6303";
			ret[41] = "6304";
			ret[42] = "6305";
			ret[43] = "6306";
			ret[44] = "6350";
			ret[45] = "6351";
			ret[46] = "6352";
			ret[47] = "6353";
			ret[48] = "6354";
			ret[49] = "6355";
			ret[50] = "6356";
			ret[51] = "6360";
			ret[52] = "6400";
			ret[53] = "6401";
			ret[54] = "6403";
			ret[55] = "6406";
			ret[56] = "6407";
			ret[57] = "6408";
			ret[58] = "6409";
			ret[59] = "6410";
			ret[60] = "6411";
			ret[61] = "6414";
			ret[62] = "6415";
			ret[63] = "6450";
			ret[64] = "6451";
			ret[65] = "6452";
			ret[66] = "6500";
			ret[67] = "6501";
			ret[68] = "6503";
			ret[69] = "6504";
			ret[70] = "6505";
			ret[71] = "6506";
			ret[72] = "6550";
			ret[73] = "6551";
			ret[74] = "6552";
			ret[75] = "6553";
			ret[76] = "6554";
			ret[77] = "6555";
			ret[78] = "6556";
			ret[79] = "6557";
			ret[80] = "6600";
			ret[81] = "6601";
			ret[82] = "6602";
			ret[83] = "6603";
			ret[84] = "6604";
			ret[85] = "6605";
			ret[86] = "6650";
			ret[87] = "6651";
			ret[88] = "6652";
			ret[89] = "6653";
			ret[90] = "6658";
			ret[91] = "6659";
			ret[92] = "6660";
			ret[93] = "6661";
			ret[94] = "6662";
			ret[95] = "6663";
			ret[96] = "6664";
			ret[97] = "6900";
			ret[98] = "6901";
			ret[99] = "6902";
			ret[100] = "6903";
			ret[101] = "6904";
			ret[102] = "6905";
			ret[103] = "6906";
			ret[104] = "6907";
			ret[105] = "6908";
			ret[106] = "6909";
			ret[107] = "6910";
			ret[108] = "6911";
			ret[109] = "6912";
			ret[110] = "6913";
			ret[111] = "6914";
			ret[112] = "6915";
			ret[113] = "6916";
			ret[114] = "6917";
			ret[115] = "6918";
			ret[116] = "6919";
			ret[117] = "6920";
			ret[118] = "6921";
			ret[119] = "6922";
			ret[120] = "6923";
			ret[121] = "6924";
			ret[122] = "6925";
			ret[123] = "6926";
			ret[124] = "6931";
			ret[125] = "6932";
			ret[126] = "6933";
			ret[127] = "6949";
		} else if (tipo == 7) {
			ret = new String[33];
			ret[0] = "7100";
			ret[1] = "7101";
			ret[2] = "7102";
			ret[3] = "7105";
			ret[4] = "7106";
			ret[5] = "7127";
			ret[6] = "7200";
			ret[7] = "7201";
			ret[8] = "7202";
			ret[9] = "7205";
			ret[10] = "7206";
			ret[11] = "7207";
			ret[12] = "7210";
			ret[13] = "7211";
			ret[14] = "7250";
			ret[15] = "7251";
			ret[16] = "7300";
			ret[17] = "7301";
			ret[18] = "7300";
			ret[19] = "7358";
			ret[20] = "7500";
			ret[21] = "7501";
			ret[22] = "7550";
			ret[23] = "7551";
			ret[24] = "7553";
			ret[25] = "7556";
			ret[26] = "7650";
			ret[27] = "7651";
			ret[28] = "7654";
			ret[29] = "7667";
			ret[30] = "7900";
			ret[31] = "7930";
			ret[32] = "7949";

		}
		return ret;
	}
	public static String[] retCFOPsCompra() {
		String[] ret = null;
		
			ret = new String[256];
			ret[0] = "1100";
			ret[1] = "1101";
			ret[2] = "1102";
			ret[3] = "1111";
			ret[4] = "1113";
			ret[5] = "1116";
			ret[6] = "1117";
			ret[7] = "1118";
			ret[8] = "1120";
			ret[9] = "1121";
			ret[10] = "1122";
			ret[11] = "1124";
			ret[12] = "1125";
			ret[13] = "1126";
			ret[14] = "1150";
			ret[15] = "1151";
			ret[16] = "1152";
			ret[17] = "1153";
			ret[18] = "1154";
			ret[19] = "1200";
			ret[20] = "1201";
			ret[21] = "1202";
			ret[22] = "1203";
			ret[23] = "1204";
			ret[24] = "1205";
			ret[25] = "1206";
			ret[26] = "1207";
			ret[27] = "1208";
			ret[28] = "1209";
			ret[29] = "1250";
			ret[30] = "1251";
			ret[31] = "1252";
			ret[32] = "1253";
			ret[33] = "1254";
			ret[34] = "1255";
			ret[35] = "1256";
			ret[36] = "1257";
			ret[37] = "1300";
			ret[38] = "1301";
			ret[39] = "1302";
			ret[40] = "1303";
			ret[41] = "1304";
			ret[42] = "1305";
			ret[43] = "1306";
			ret[44] = "1350";
			ret[45] = "1351";
			ret[46] = "1352";
			ret[47] = "1353";
			ret[48] = "1354";
			ret[49] = "1355";
			ret[50] = "1356";
			ret[51] = "1360";
			ret[52] = "1400";
			ret[53] = "1401";
			ret[54] = "1403";
			ret[55] = "1406";
			ret[56] = "1407";
			ret[57] = "1408";
			ret[58] = "1409";
			ret[59] = "1410";
			ret[60] = "1411";
			ret[61] = "1414";
			ret[62] = "1415";
			ret[63] = "1450";
			ret[64] = "1451";
			ret[65] = "1452";
			ret[66] = "1500";
			ret[67] = "1501";
			ret[68] = "1503";
			ret[69] = "1504";
			ret[70] = "1505";
			ret[71] = "1506";
			ret[72] = "1550";
			ret[73] = "1551";
			ret[74] = "1552";
			ret[75] = "1553";
			ret[76] = "1554";
			ret[77] = "1555";
			ret[78] = "1556";
			ret[79] = "1557";
			ret[80] = "1600";
			ret[81] = "1601";
			ret[82] = "1602";
			ret[83] = "1603";
			ret[84] = "1604";
			ret[85] = "1605";
			ret[86] = "1650";
			ret[87] = "1651";
			ret[88] = "1652";
			ret[89] = "1653";
			ret[90] = "1658";
			ret[91] = "1659";
			ret[92] = "1660";
			ret[93] = "1661";
			ret[94] = "1662";
			ret[95] = "1663";
			ret[96] = "1664";
			ret[97] = "1900";
			ret[98] = "1901";
			ret[99] = "1902";
			ret[100] = "1903";
			ret[101] = "1904";
			ret[102] = "1905";
			ret[103] = "1906";
			ret[104] = "1907";
			ret[105] = "1908";
			ret[106] = "1909";
			ret[107] = "1910";
			ret[108] = "1911";
			ret[109] = "1912";
			ret[110] = "1913";
			ret[111] = "1914";
			ret[112] = "1915";
			ret[113] = "1916";
			ret[114] = "1917";
			ret[115] = "1918";
			ret[116] = "1919";
			ret[117] = "1920";
			ret[118] = "1921";
			ret[119] = "1922";
			ret[120] = "1923";
			ret[121] = "1924";
			ret[122] = "1925";
			ret[123] = "1926";
			ret[124] = "1931";
			ret[125] = "1932";
			ret[126] = "1933";
			ret[127] = "1949";
		
			
			ret[128] = "2100";
			ret[129] = "2101";
			ret[130] = "2102";
			ret[131] = "2111";
			ret[132] = "2113";
			ret[133] = "2116";
			ret[134] = "2117";
			ret[135] = "2118";
			ret[136] = "2120";
			ret[137] = "2121";
			ret[138] = "2122";
			ret[139] = "2124";
			ret[140] = "2125";
			ret[141] = "2126";
			ret[142] = "2150";
			ret[143] = "2151";
			ret[144] = "2152";
			ret[145] = "2153";
			ret[146] = "2154";
			ret[147] = "2200";
			ret[148] = "2201";
			ret[149] = "2202";
			ret[150] = "2203";
			ret[151] = "2204";
			ret[152] = "2205";
			ret[153] = "2206";
			ret[154] = "2207";
			ret[155] = "2208";
			ret[156] = "2209";
			ret[157] = "2250";
			ret[158] = "2251";
			ret[159] = "2252";
			ret[160] = "2253";
			ret[161] = "2254";
			ret[162] = "2255";
			ret[163] = "2256";
			ret[164] = "2257";
			ret[165] = "2300";
			ret[166] = "2301";
			ret[167] = "2302";
			ret[168] = "2303";
			ret[169] = "2304";
			ret[170] = "2305";
			ret[171] = "2306";
			ret[172] = "2350";
			ret[173] = "2351";
			ret[174] = "2352";
			ret[175] = "2353";
			ret[176] = "2354";
			ret[177] = "2355";
			ret[178] = "2356";
			ret[179] = "2360";
			ret[180] = "2400";
			ret[181] = "2401";
			ret[182] = "2403";
			ret[183] = "2406";
			ret[184] = "2407";
			ret[185] = "2408";
			ret[186] = "2409";
			ret[187] = "2410";
			ret[188] = "2411";
			ret[189] = "2414";
			ret[190] = "2415";
			ret[191] = "2450";
			ret[192] = "2451";
			ret[193] = "2452";
			ret[194] = "2500";
			ret[195] = "2501";
			ret[196] = "2503";
			ret[197] = "2504";
			ret[198] = "2505";
			ret[199] = "2506";
			ret[200] = "2550";
			ret[201] = "2551";
			ret[202] = "2552";
			ret[203] = "2553";
			ret[204] = "2554";
			ret[205] = "2555";
			ret[206] = "2556";
			ret[207] = "2557";
			ret[208] = "2600";
			ret[209] = "2601";
			ret[210] = "2602";
			ret[211] = "2603";
			ret[212] = "2604";
			ret[213] = "2605";
			ret[214] = "2650";
			ret[215] = "2651";
			ret[216] = "2652";
			ret[217] = "2653";
			ret[218] = "2658";
			ret[219] = "2659";
			ret[220] = "2660";
			ret[221] = "2661";
			ret[222] = "2662";
			ret[223] = "2663";
			ret[224] = "2664";
			ret[225] = "2900";
			ret[226] = "2901";
			ret[227] = "2902";
			ret[228] = "2903";
			ret[229] = "2904";
			ret[230] = "2905";
			ret[231] = "2906";
			ret[232] = "2907";
			ret[233] = "2908";
			ret[234] = "2909";
			ret[235] = "2910";
			ret[236] = "2911";
			ret[237] = "2912";
			ret[238] = "2913";
			ret[239] = "2914";
			ret[240] = "2915";
			ret[241] = "2916";
			ret[242] = "2917";
			ret[243] = "2918";
			ret[244] = "2919";
			ret[245] = "2920";
			ret[246] = "2921";
			ret[247] = "2922";
			ret[248] = "2923";
			ret[249] = "2924";
			ret[250] = "2925";
			ret[251] = "2926";
			ret[252] = "2931";
			ret[253] = "2932";
			ret[254] = "2933";
			ret[255] = "2949";
		
		return ret;
	}
	public static String[] retCFOPsVenda() {
		String[] ret = null;
		
			ret = new String[256];
			ret[0] = "5100";
			ret[1] = "5101";
			ret[2] = "5102";
			ret[3] = "5111";
			ret[4] = "5113";
			ret[5] = "5116";
			ret[6] = "5117";
			ret[7] = "5118";
			ret[8] = "5120";
			ret[9] = "5121";
			ret[10] = "5122";
			ret[11] = "5124";
			ret[12] = "5125";
			ret[13] = "5126";
			ret[14] = "5150";
			ret[15] = "5151";
			ret[16] = "5152";
			ret[17] = "5153";
			ret[18] = "5154";
			ret[19] = "5200";
			ret[20] = "5201";
			ret[21] = "5202";
			ret[22] = "5203";
			ret[23] = "5204";
			ret[24] = "5205";
			ret[25] = "5206";
			ret[26] = "5207";
			ret[27] = "5208";
			ret[28] = "5209";
			ret[29] = "5250";
			ret[30] = "5251";
			ret[31] = "5252";
			ret[32] = "5253";
			ret[33] = "5254";
			ret[34] = "5255";
			ret[35] = "5256";
			ret[36] = "5257";
			ret[37] = "5300";
			ret[38] = "5301";
			ret[39] = "5302";
			ret[40] = "5303";
			ret[41] = "5304";
			ret[42] = "5305";
			ret[43] = "5306";
			ret[44] = "5350";
			ret[45] = "5351";
			ret[46] = "5352";
			ret[47] = "5353";
			ret[48] = "5354";
			ret[49] = "5355";
			ret[50] = "5356";
			ret[51] = "5360";
			ret[52] = "5400";
			ret[53] = "5401";
			ret[54] = "5403";
			ret[55] = "5406";
			ret[56] = "5407";
			ret[57] = "5408";
			ret[58] = "5409";
			ret[59] = "5410";
			ret[60] = "5411";
			ret[61] = "5414";
			ret[62] = "5415";
			ret[63] = "5450";
			ret[64] = "5451";
			ret[65] = "5452";
			ret[66] = "5500";
			ret[67] = "5501";
			ret[68] = "5503";
			ret[69] = "5504";
			ret[70] = "5505";
			ret[71] = "5506";
			ret[72] = "5550";
			ret[73] = "5551";
			ret[74] = "5552";
			ret[75] = "5553";
			ret[76] = "5554";
			ret[77] = "5555";
			ret[78] = "5556";
			ret[79] = "5557";
			ret[80] = "5600";
			ret[81] = "5601";
			ret[82] = "5602";
			ret[83] = "5603";
			ret[84] = "5604";
			ret[85] = "5605";
			ret[86] = "5650";
			ret[87] = "5651";
			ret[88] = "5652";
			ret[89] = "5653";
			ret[90] = "5658";
			ret[91] = "5659";
			ret[92] = "5660";
			ret[93] = "5661";
			ret[94] = "5662";
			ret[95] = "5663";
			ret[96] = "5664";
			ret[97] = "5900";
			ret[98] = "5901";
			ret[99] = "5902";
			ret[100] = "5903";
			ret[101] = "5904";
			ret[102] = "5905";
			ret[103] = "5906";
			ret[104] = "5907";
			ret[105] = "5908";
			ret[106] = "5909";
			ret[107] = "5910";
			ret[108] = "5911";
			ret[109] = "5912";
			ret[110] = "5913";
			ret[111] = "5914";
			ret[112] = "5915";
			ret[113] = "5916";
			ret[114] = "5917";
			ret[115] = "5918";
			ret[116] = "5919";
			ret[117] = "5920";
			ret[118] = "5921";
			ret[119] = "5922";
			ret[120] = "5923";
			ret[121] = "5924";
			ret[122] = "5925";
			ret[123] = "5926";
			ret[124] = "5931";
			ret[125] = "5932";
			ret[126] = "5933";
			ret[127] = "5949";
		
		
			ret[128] = "6100";
			ret[129] = "6101";
			ret[130] = "6102";
			ret[131] = "6111";
			ret[132] = "6113";
			ret[133] = "6116";
			ret[134] = "6117";
			ret[135] = "6118";
			ret[136] = "6120";
			ret[137] = "6121";
			ret[138] = "6122";
			ret[139] = "6124";
			ret[140] = "6125";
			ret[141] = "6126";
			ret[142] = "6150";
			ret[143] = "6151";
			ret[144] = "6152";
			ret[145] = "6153";
			ret[146] = "6154";
			ret[147] = "6200";
			ret[148] = "6201";
			ret[149] = "6202";
			ret[150] = "6203";
			ret[151] = "6204";
			ret[152] = "6205";
			ret[153] = "6206";
			ret[154] = "6207";
			ret[155] = "6208";
			ret[156] = "6209";
			ret[157] = "6250";
			ret[158] = "6251";
			ret[159] = "6252";
			ret[160] = "6253";
			ret[161] = "6254";
			ret[162] = "6255";
			ret[163] = "6256";
			ret[164] = "6257";
			ret[165] = "6300";
			ret[166] = "6301";
			ret[167] = "6302";
			ret[168] = "6303";
			ret[169] = "6304";
			ret[170] = "6305";
			ret[171] = "6306";
			ret[172] = "6350";
			ret[173] = "6351";
			ret[174] = "6352";
			ret[175] = "6353";
			ret[176] = "6354";
			ret[177] = "6355";
			ret[178] = "6356";
			ret[179] = "6360";
			ret[180] = "6400";
			ret[181] = "6401";
			ret[182] = "6403";
			ret[183] = "6406";
			ret[184] = "6407";
			ret[185] = "6408";
			ret[186] = "6409";
			ret[187] = "6410";
			ret[188] = "6411";
			ret[189] = "6414";
			ret[190] = "6415";
			ret[191] = "6450";
			ret[192] = "6451";
			ret[193] = "6452";
			ret[194] = "6500";
			ret[195] = "6501";
			ret[196] = "6503";
			ret[197] = "6504";
			ret[198] = "6505";
			ret[199] = "6506";
			ret[200] = "6550";
			ret[201] = "6551";
			ret[202] = "6552";
			ret[203] = "6553";
			ret[204] = "6554";
			ret[205] = "6555";
			ret[206] = "6556";
			ret[207] = "6557";
			ret[208] = "6600";
			ret[209] = "6601";
			ret[210] = "6602";
			ret[211] = "6603";
			ret[212] = "6604";
			ret[213] = "6605";
			ret[214] = "6650";
			ret[215] = "6651";
			ret[216] = "6652";
			ret[217] = "6653";
			ret[218] = "6658";
			ret[219] = "6659";
			ret[220] = "6660";
			ret[221] = "6661";
			ret[222] = "6662";
			ret[223] = "6663";
			ret[224] = "6664";
			ret[225] = "6900";
			ret[226] = "6901";
			ret[227] = "6902";
			ret[228] = "6903";
			ret[229] = "6904";
			ret[230] = "6905";
			ret[231] = "6906";
			ret[232] = "6907";
			ret[233] = "6908";
			ret[234] = "6909";
			ret[235] = "6910";
			ret[236] = "6911";
			ret[237] = "6912";
			ret[238] = "6913";
			ret[239] = "6914";
			ret[240] = "6915";
			ret[241] = "6916";
			ret[242] = "6917";
			ret[243] = "6918";
			ret[244] = "6919";
			ret[245] = "6920";
			ret[246] = "6921";
			ret[247] = "6922";
			ret[248] = "6923";
			ret[249] = "6924";
			ret[250] = "6925";
			ret[251] = "6926";
			ret[252] = "6931";
			ret[253] = "6932";
			ret[254] = "6933";
			ret[255] = "6949";
		
		return ret;
	}
	public static String dataPMySQL(String data) {
		String ret = "    -  -  ";
		try {
			data = data.replace("/", "");
		} catch (Exception e) {
		}
		try {
			data = data.replace("-", "");
		} catch (Exception e) {
		}
		try {
			data = data.substring(4, 8) + "-" + data.substring(2, 4) + "-"
					+ data.substring(0, 2);
		} catch (Exception e) {
		}
		ret = data;
		return ret;
	}
	public static String retData(String date) {
		String ret = "";
		Date data = new Date();
		String d = "";
		String di = "";
		String me = "";
		String an = "";
		int diaAtual = 0;
		int mesAtual = 0;
		int anoAtual = 0;
		// --
		String diaSS = "";
		String mesSS = "";
		String anoSS = "";
		int diaSet = 0;
		int mesSet = 0;
		int anoSet = 0;
		try {
			diaSS = date.substring(0, 2);
		} catch (Exception e) {
		}
		try {
			mesSS = date.substring(3, 5);
		} catch (Exception e) {
		}
		try {
			anoSS = date.substring(6, 10);
		} catch (Exception e) {
		}
		try {
			diaSet = Integer.parseInt(diaSS);
		} catch (Exception e) {
		}
		try {
			mesSet = Integer.parseInt(mesSS);
		} catch (Exception e) {
		}
		try {
			anoSet = Integer.parseInt(anoSS);
		} catch (Exception e) {
		}

		SimpleDateFormat dataC = new SimpleDateFormat("ddMMyyyy");
		SimpleDateFormat dia = new SimpleDateFormat("dd");
		SimpleDateFormat mes = new SimpleDateFormat("MM");
		SimpleDateFormat ano = new SimpleDateFormat("yyyy");
		d = dataC.format(data);
		di = dia.format(data);
		me = mes.format(data);
		an = ano.format(data);
		try {
			diaAtual = Integer.parseInt(di);
		} catch (Exception e) {
		}
		try {
			mesAtual = Integer.parseInt(me);
		} catch (Exception e) {
		}
		try {
			anoAtual = Integer.parseInt(an);
		} catch (Exception e) {
		}
		if (diaSet <= 0 || diaSet > 31) {
			diaSet = diaAtual;
		}
		if (mesSet <= 0 || mesSet > 12) {
			mesSet = mesAtual;
		}
		if (anoSet < 1900) {
			anoSet = anoAtual;
		}
		di = String.valueOf(diaSet);
		me = String.valueOf(mesSet);
		an = String.valueOf(anoSet);
		if (di.length() == 1) {
			di = "0" + di;
		}
		if (me.length() == 1) {
			me = "0" + me;
		}

		ret = di + me + an;
		ret = dataCorreta(di + "/" + me + "/" + an);
		return ret;
	}
	public static int stringPInt(String val) {
		int ret = 0;
		try {
			val = val.replace("-", "");
		} catch (Exception e) {
		}
		try {
			val = val.replace(".", "");
		} catch (Exception e) {
		}
		try {
			val = val.replace(",", ".");
		} catch (Exception e) {
		}
		try {
			ret = Integer.parseInt(val);
		} catch (Exception e) {
		}
		return ret;
	}
	public static String dataCorreta(String data) {
		String ret = "00/00/0000";
		String diaS = "";
		int dia = 1;
		try {
			diaS = data.substring(0, 2);
		} catch (Exception e) {
		}
		dia = stringPInt(diaS);
		//
		String mesS = "";
		try {
			mesS = data.substring(3, 5);
		} catch (Exception e) {
		}
		//
		String anoS = "";
		try {
			anoS = data.substring(6, 10);
		} catch (Exception e) {
		}
		//
		String dataUltimoDia = retDataUltimoDiaMes("01" + "/" + mesS + "/"
				+ anoS);
		int ultimoDiaMes = stringPInt(dataUltimoDia.substring(0, 2));
		if (ultimoDiaMes < dia) {
			dia = ultimoDiaMes;
		}
		diaS = String.valueOf(dia);
		diaS = dia < 10 ? "0" + diaS : diaS;
		ret = diaS + "/" + mesS + "/" + anoS;
		return ret;
	}
	public static String dataAtualText() {
		// cria o formatador
		String ret = "";
		try {
			oConn = (Connection) Conexao.abrirConexao();
			stmt = (Statement) oConn.createStatement();
			rs = (ResultSet) stmt.executeQuery("SELECT DATE_FORMAT(curdate(), '%d/%m/%Y') as data_atual");
			
			if(rs.next()) {
				ret = rs.getString("data_atual");
			}			
		} catch (Exception e) {
			e.printStackTrace();			
		}finally{
			Conexao.fecharConexao();
			try{rs.close();}catch(Exception e){}
			try{oConn.close();}catch(Exception e){}
			try{stmt.close();}catch(Exception e){}
		}
		return ret;
	}
	public static String retDataUltimoDiaMes(String dataA) {
		String ret = "";
		try {
			SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
			//Date date = sd.parse(dataA);
			int ano = Integer.parseInt(dataA.substring(6, 10));
			int mes = Integer.parseInt(dataA.substring(3, 5));
			String mesS = dataA.substring(3, 5);
			int dia = Integer.parseInt(dataA.substring(0, 2));
			// dataA = sd.format(date);
			Calendar c = new GregorianCalendar(ano, mes - 1, dia);
			for (int i = 0; i < 32; i++) { //

				// c.add(Calendar.DATE, dias);//Adiciona os dias na data
				c.add(Calendar.DATE, 1);
				// c.set(ano, mes, dia);
				ret = sd.format(c.getTime());
				String mesFor = ret.substring(3, 5);
				if (!mesS.equals(mesFor)) {
					break;
				}
			}
			c.add(Calendar.DATE, -1);
			ret = sd.format(c.getTime());
			// System.out.println("ULTIMO DIA: "+ret);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	public static String retHora(String hora, boolean bcoTrue) {
		String ret = "";
		try {
			hora = hora.replace(":", "");
		} catch (Exception e) {
		}
		String h = "";
		String m = "";
		boolean hor = false;
		boolean min = false;
		if (hora.equals("    ") && bcoTrue == true) {
			ret = "  :  ";
		} else {
			if (hora.length() == 4) {
				h = hora.substring(0, 2);
				m = hora.substring(2, 4);
				int hi = -1;
				int mi = -1;
				try {
					hi = Integer.parseInt(h);
				} catch (Exception e) {
				}
				try {
					mi = Integer.parseInt(m);
				} catch (Exception e) {
				}
				// ---------------------------\\
				if (hi >= 0 && hi <= 23) {
					hor = true;
				} else {
					h = hhAtualText();
				}
				if (mi >= 0 && mi <= 59) {
					min = true;
				} else {
					m = mmAtualText();
				}
				if (hor == true && min == true) {
					ret = h + ":" + m;
				} else {
					ret = horaAtualText();
				}
			} else {
				ret = horaAtualText();
			}
			if (hora.equals("  :  ") && bcoTrue == true) {
				ret = "  :  ";
			}
		}

		return ret;
	}
	public static String hhAtualText() {
		// cria o formatador
		Date data = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("HH");
		// cria a string
		String dataA = formatador.format(data);
		return dataA;
	}

	public static String mmAtualText() {
		// cria o formatador
		Date data = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("mm");
		// cria a string
		String dataA = formatador.format(data);
		return dataA;
	}
	public static String horaAtualText() {
		// cria o formatador
		Date data = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("HH:mm");
		// cria a string
		String dataA = formatador.format(data);
		return dataA;
	}
	public static String horaAtualHHMM() {
		// cria o formatador
		Date data = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("hh:mm");
		// cria a string
		String dataA = formatador.format(data);
		return dataA;
	}

	public static String horaAtualHH() {
		// cria o formatador
		Date data = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("hh");
		// cria a string
		String dataA = formatador.format(data);
		return dataA;
	}

	public static String horaAtualMM() {
		// cria o formatador
		Date data = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("mm");
		// cria a string
		String dataA = formatador.format(data);
		return dataA;
	}
	
	
	public static int jOptionPaneQuestion(String msg) {
		int ret = 1;
		JLabel input = new JLabel();
		String[] options = { "Sim", "Não" };
		Object[] message = { msg, input };
		int result = JOptionPane.showOptionDialog(null, message, "ATENÇÃO!",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				options, options[0]);
		switch (result) {
		case 0: // SIM
			ret = 0;
			// ...
			break;
		case 1: // NAO
			ret = 1;
			break;
		}
		return ret;
	}

	public static int jOptionPaneInformation(String msg) {
		int ret = 1;
		JLabel input = new JLabel();
		String[] options = { "Ok" };
		Object[] message = { msg, input };
		int result = JOptionPane.showOptionDialog(null, message, "ATENÇÃO!",
				JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
				null, options, options[0]);
		switch (result) {
		case 0: // SIM
			ret = 0;
			// ...
			break;
		case 1: // NAO
			ret = 1;
			break;
		}
		return ret;
	}

	public static int jOptionPaneError(String msg) {
		int ret = 1;
		JLabel input = new JLabel();
		String[] options = { "Ok" };
		Object[] message = { msg, input };
		int result = JOptionPane.showOptionDialog(null, message,
				"ATENÇÃO!", JOptionPane.YES_NO_OPTION,
				JOptionPane.ERROR_MESSAGE, null, options, options[0]);
		switch (result) {
		case 0: // SIM
			ret = 0;
			// ...
			break;
		case 1: // NAO
			ret = 1;
			break;
		}
		return ret;
	}

	public static int jOptionPaneAlert(String msg) {
		int ret = 1;
		JLabel input = new JLabel();
		String[] options = { "Ok" };
		Object[] message = { msg, input };
		int result = JOptionPane.showOptionDialog(null, message,
				"ATENÇÃO!", JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE, null, options, options[0]);
		switch (result) {
		case 0: // SIM
			ret = 0;
			// ...
			break;
		case 1: // NAO
			ret = 1;
			break;
		}
		return ret;
	}

	public static int jOptionPanePlain(String msg) {
		int ret = 1;
		JLabel input = new JLabel();
		String[] options = { "Ok" };
		Object[] message = { msg, input };
		int result = JOptionPane.showOptionDialog(null, message,
				"ATENÇÃO!", JOptionPane.YES_NO_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		switch (result) {
		case 0: // SIM
			ret = 0;
			// ...
			break;
		case 1: // NAO
			ret = 1;
			break;
		}
		return ret;
	}

	public static boolean jOptionPanePassword(String msg, String senhaCorreta) {
		boolean ret = false;
		JPasswordField password = new JPasswordField(10);
		password.setEchoChar('*');
		// Cria um rótulo para o campo
		JLabel rotulo = new JLabel(msg);
		// Coloca o rótulo e a caixa de entrada numa JPanel:
		JPanel entUsuario = new JPanel();
		entUsuario.add(rotulo);
		entUsuario.add(password);
		JOptionPane.showMessageDialog(null, entUsuario,
				"Comando restrito!", JOptionPane.INFORMATION_MESSAGE);
		String senha = password.getText();
		if (senha.equals(senhaCorreta)) {
			ret = true;
		}
		return ret;
	}

	public static String jOptionPanePassword2(String msg) {
		String ret = "";
		JPasswordField password = new JPasswordField(10);
		password.setEchoChar('*');
		// Cria um rótulo para o campo
		JLabel rotulo = new JLabel(msg);
		// Coloca o rótulo e a caixa de entrada numa JPanel:
		JPanel entUsuario = new JPanel();
		entUsuario.add(rotulo);
		entUsuario.add(password);
		JOptionPane.showMessageDialog(null, entUsuario,
				"Comando restrito!", JOptionPane.INFORMATION_MESSAGE);
		ret = password.getText();
		return ret;
	}

	public static int jOptionPaneTipoImpressora() {
		int ret = -1;
		String msg = "Selecione o Tipo de Impressora:";
		JComboBox combo = new JComboBox();
		combo.addItem("Laser");
		combo.addItem("Matricial");
		// Cria um rótulo para o campo
		JLabel rotulo = new JLabel(msg);
		// Coloca o rótulo e a caixa de entrada numa JPanel:
		JPanel entUsuario = new JPanel();
		entUsuario.add(rotulo);
		entUsuario.add(combo);
		JOptionPane.showMessageDialog(null, entUsuario, "Imprimir!",
				JOptionPane.INFORMATION_MESSAGE);
		String tip = combo.getSelectedItem().toString();
		if (tip.equals("Laser")) {
			ret = 0;
		} else {
			ret = 1;
		}
		return ret;
	}
	public static String quatroDigitos(String num) {
		String ret = "";
		try {
			if (num.length() == 1) {
				ret = "000" + num;
			} else if (num.length() == 2) {
				ret = "00" + num;
			} else if (num.length() == 3) {
				ret = "0" + num;
			} else {
				ret = num;
			}
		} catch (Exception e) {
		}
		return ret;

	}

	//
	public static String cincoDigitos(String num) {
		String ret = "";
		try {
			if (num.length() == 1) {
				ret = "0000" + num;
			} else if (num.length() == 2) {
				ret = "000" + num;
			} else if (num.length() == 3) {
				ret = "00" + num;
			} else if (num.length() == 4) {
				ret = "0" + num;
			} else {
				ret = num;
			}
		} catch (Exception e) {
		}
		return ret;

	}

	//
	public static String seisDigitos(String num) {
		String ret = "";
		try {
			if (num.length() == 1) {
				ret = "00000" + num;
			} else if (num.length() == 2) {
				ret = "0000" + num;
			} else if (num.length() == 3) {
				ret = "000" + num;
			} else if (num.length() == 4) {
				ret = "00" + num;
			} else if (num.length() == 5) {
				ret = "0" + num;
			} else {
				ret = num;
			}
		} catch (Exception e) {
		}
		return ret;
	}
	public static String seteDigitos(String num) {
		String ret = "";
		try {
			if (num.length() == 1) {
				ret = "000000" + num;
			} else if (num.length() == 2) {
				ret = "00000" + num;
			} else if (num.length() == 3) {
				ret = "0000" + num;
			} else if (num.length() == 4) {
				ret = "000" + num;
			} else if (num.length() == 5) {
				ret = "00" + num;
			} else if (num.length() == 6) {
				ret = "0" + num;
			} else {
				ret = num;
			}
		} catch (Exception e) {
		}
		return ret;
	}

	public static String noveDigitos(String num) {
		String ret = "";
		try {
			if (num.length() == 1) {
				ret = "00000000" + num;
			} else if (num.length() == 2) {
				ret = "0000000" + num;
			} else if (num.length() == 3) {
				ret = "000000" + num;
			} else if (num.length() == 4) {
				ret = "00000" + num;
			} else if (num.length() == 5) {
				ret = "0000" + num;
			} else if (num.length() == 6) {
				ret = "000" + num;
			} else if (num.length() == 7) {
				ret = "00" + num;
			} else if (num.length() == 8) {
				ret = "0" + num;
			} else {
				ret = num;
			}
		} catch (Exception e) {
		}
		return ret;
	}

	public static String treisDigitos(String num) {
		String ret = "";
		try {
			if (num.length() == 1) {
				ret = "00" + num;
			} else if (num.length() == 2) {
				ret = "0" + num;
			} else {
				ret = num;
			}
		} catch (Exception e) {
		}
		return ret;
	}
	public static String horaAtualHHMMSS() {
		// cria o formatador
		Date data = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("hh:mm:ss");
		// cria a string
		String dataA = formatador.format(data);
		return dataA;
	}
	public static String dataAtualSql() {
		// cria o formatador
		String ret = "";
		try {
			oConn = (Connection) Conexao.abrirConexao();
			stmt = (Statement) oConn.createStatement();
			rs = (ResultSet) stmt.executeQuery("SELECT current_date as data_atual");
			
			if(rs.next()) {
				ret = rs.getString("data_atual");
			}			
		} catch (Exception e) {
			//e.printStackTrace();			
		}finally{
			Conexao.fecharConexao();
			try{rs.close();}catch(Exception e){}
			try{oConn.close();}catch(Exception e){}
			try{stmt.close();}catch(Exception e){}
		}
		return ret;
	}

	public static String dezDigitos(String num) {
		String ret = "";
		try {
			if (num.length() == 1) {
				ret = "000000000" + num;
			} else if (num.length() == 2) {
				ret = "00000000" + num;
			} else if (num.length() == 3) {
				ret = "0000000" + num;
			} else if (num.length() == 4) {
				ret = "000000" + num;
			} else if (num.length() == 5) {
				ret = "00000" + num;
			} else if (num.length() == 6) {
				ret = "0000" + num;
			} else if (num.length() == 7) {
				ret = "000" + num;
			} else if (num.length() == 8) {
				ret = "00" + num;
			} else if (num.length() == 9) {
				ret = "0" + num;
			} else {
				ret = num;
			}
		} catch (Exception e) {
		}
		return ret;
	}

	public static String onzeDigitos(String num) {
		String ret = "";
		try {
			if (num.length() == 1) {
				ret = "0000000000" + num;
			} else if (num.length() == 2) {
				ret = "000000000" + num;
			} else if (num.length() == 3) {
				ret = "00000000" + num;
			} else if (num.length() == 4) {
				ret = "0000000" + num;
			} else if (num.length() == 5) {
				ret = "000000" + num;
			} else if (num.length() == 6) {
				ret = "00000" + num;
			} else if (num.length() == 7) {
				ret = "0000" + num;
			} else if (num.length() == 8) {
				ret = "000" + num;
			} else if (num.length() == 9) {
				ret = "00" + num;
			} else if (num.length() == 10) {
				ret = "0" + num;
			} else {
				ret = num;
			}
		} catch (Exception e) {
		}
		return ret;
	}
	public static String quinzeDigitos(String num) {
		String ret = "";
		try {
			if (num.length() == 1) {
				ret = "00000000000000" + num;
			} else if (num.length() == 2) {
				ret = "0000000000000" + num;
			} else if (num.length() == 3) {
				ret = "000000000000" + num;
			} else if (num.length() == 4) {
				ret = "00000000000" + num;
			} else if (num.length() == 5) {
				ret = "0000000000" + num;
			} else if (num.length() == 6) {
				ret = "000000000" + num;
			} else if (num.length() == 7) {
				ret = "00000000" + num;
			} else if (num.length() == 8) {
				ret = "0000000" + num;
			} else if (num.length() == 9) {
				ret = "000000" + num;
			} else if (num.length() == 10) {
				ret = "00000" + num;
			} else if (num.length() == 11) {
				ret = "0000" + num;
			} else if (num.length() == 12) {
				ret = "000" + num;
			} else if (num.length() == 13) {
				ret = "00" + num;
			}else if (num.length() == 14) {
				ret = "0" + num;
			} else {
				ret = num;
			}
		} catch (Exception e) {
		}
		return ret;
	}
	public static String dozeDigitos(String num) {
		String ret = "";
		try {
			if (num.length() == 1) {
				ret = "00000000000" + num;
			} else if (num.length() == 2) {
				ret = "0000000000" + num;
			} else if (num.length() == 3) {
				ret = "000000000" + num;
			} else if (num.length() == 4) {
				ret = "00000000" + num;
			} else if (num.length() == 5) {
				ret = "0000000" + num;
			} else if (num.length() == 6) {
				ret = "000000" + num;
			} else if (num.length() == 7) {
				ret = "00000" + num;
			} else if (num.length() == 8) {
				ret = "0000" + num;
			} else if (num.length() == 9) {
				ret = "000" + num;
			} else if (num.length() == 10) {
				ret = "00" + num;
			} else if (num.length() == 11) {
				ret = "0" + num;
			} else {
				ret = num;
			}
		} catch (Exception e) {
		}
		return ret;
	}
	public static String quatorzeDigitos(String num) {
		String ret = "";
		try {
			if (num.length() == 1) {
				ret = "0000000000000" + num;
			} else if (num.length() == 2) {
				ret = "000000000000" + num;
			} else if (num.length() == 3) {
				ret = "00000000000" + num;
			} else if (num.length() == 4) {
				ret = "0000000000" + num;
			} else if (num.length() == 5) {
				ret = "000000000" + num;
			} else if (num.length() == 6) {
				ret = "00000000" + num;
			} else if (num.length() == 7) {
				ret = "0000000" + num;
			} else if (num.length() == 8) {
				ret = "000000" + num;
			} else if (num.length() == 9) {
				ret = "00000" + num;
			} else if (num.length() == 10) {
				ret = "0000" + num;
			} else if (num.length() == 11) {
				ret = "000" + num;
			} else if (num.length() == 12) {
				ret = "00" + num;
			} else if (num.length() == 13) {
				ret = "0" + num;
			} else {
				ret = num;
			}
		} catch (Exception e) {
		}
		return ret;
	}

	public static String dezesseteDigitos(String num) {
		String ret = "";
		try {
			if (num.length() == 1) {
				ret = "0000000000000000" + num;
			} else if (num.length() == 2) {
				ret = "000000000000000" + num;
			} else if (num.length() == 3) {
				ret = "00000000000000" + num;
			} else if (num.length() == 4) {
				ret = "0000000000000" + num;
			} else if (num.length() == 5) {
				ret = "000000000000" + num;
			} else if (num.length() == 6) {
				ret = "00000000000" + num;
			} else if (num.length() == 7) {
				ret = "0000000000" + num;
			} else if (num.length() == 8) {
				ret = "000000000" + num;
			} else if (num.length() == 9) {
				ret = "00000000" + num;
			} else if (num.length() == 10) {
				ret = "0000000" + num;
			} else if (num.length() == 11) {
				ret = "000000" + num;
			} else if (num.length() == 12) {
				ret = "00000" + num;
			} else if (num.length() == 13) {
				ret = "0000" + num;
			} else if (num.length() == 14) {
				ret = "000" + num;
			} else if (num.length() == 15) {
				ret = "00" + num;
			} else if (num.length() == 16) {
				ret = "0" + num;
			} else {
				ret = num;
			}
		} catch (Exception e) {
		}
		return ret;
	}

	public static String doisDigitos(String num) {
		String ret = "";
		try {
			if (num.length() == 1) {
				ret = "0" + num;
			} else {
				ret = num;
			}

		} catch (Exception e) {
		}
		return ret;
	}
	public static String dataAAAAatualSql() {
		// cria o formatador
		Date data = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("yyyy");
		// cria a string
		String dataA = formatador.format(data);
		return dataA;
	}
	public static String jOptionPaneCTextValor(String msg, String msg2,
			String msg3) {
		String ret = "";
		JTextField text = new JTextField();
		text.setLocation(new Point(90, 6));
		text.setDocument(new MonetarioDocumentVirg());
		text.setHorizontalAlignment(SwingConstants.TRAILING);
		text.setSize(new Dimension(80, 21));
		text.setText("0");
		JLabel rotulo = new JLabel(msg);
		rotulo.setLocation(new Point(0, 6));
		rotulo.setSize(new Dimension(80, 21));
		rotulo.setHorizontalAlignment(SwingConstants.LEADING);
		JLabel rotulo2 = new JLabel(msg2);
		rotulo.setLocation(new Point(0, 6));
		rotulo.setSize(new Dimension(80, 21));
		rotulo.setHorizontalAlignment(SwingConstants.LEADING);
		JLabel rotulo3 = new JLabel(msg3);
		rotulo.setLocation(new Point(0, 6));
		rotulo.setSize(new Dimension(80, 21));
		rotulo.setHorizontalAlignment(SwingConstants.LEADING);
		// Coloca o rótulo e a caixa de entrada numa JPanel:
		JPanel entUsuario = new JPanel();
		entUsuario.setSize(500, 500);
		// entUsuario.setLayout(null);
		GridLayout gridLayout = new GridLayout();
		gridLayout.setRows(4);
		entUsuario.setLayout(gridLayout);
		entUsuario.add(rotulo);
		entUsuario.add(rotulo2);
		entUsuario.add(rotulo3);
		entUsuario.add(text);
		JOptionPane.showMessageDialog(null, entUsuario, "Atenção!",
				JOptionPane.INFORMATION_MESSAGE);

		ret = text.getText();
		return ret;
	}
	public static String dataMMatualSql() {
		// cria o formatador
		Date data = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("MM");
		// cria a string
		String dataA = formatador.format(data);
		return dataA;
	}
	public static String jOptionPaneCTextSomenteNumeros(String msg, String msg2,
			String msg3) {
		String ret = "";
		JTextField text = new JTextField();
		text.setLocation(new Point(90, 6));
		text.setDocument(new SomenteNumeros8());
		text.setHorizontalAlignment(SwingConstants.TRAILING);
		text.setSize(new Dimension(80, 21));
		text.setText("0");
		JLabel rotulo = new JLabel(msg);
		rotulo.setLocation(new Point(0, 6));
		rotulo.setSize(new Dimension(80, 21));
		rotulo.setHorizontalAlignment(SwingConstants.LEADING);
		JLabel rotulo2 = new JLabel(msg2);
		rotulo.setLocation(new Point(0, 6));
		rotulo.setSize(new Dimension(80, 21));
		rotulo.setHorizontalAlignment(SwingConstants.LEADING);
		JLabel rotulo3 = new JLabel(msg3);
		rotulo.setLocation(new Point(0, 6));
		rotulo.setSize(new Dimension(80, 21));
		rotulo.setHorizontalAlignment(SwingConstants.LEADING);
		// Coloca o rótulo e a caixa de entrada numa JPanel:
		JPanel entUsuario = new JPanel();
		entUsuario.setSize(500, 500);
		// entUsuario.setLayout(null);
		GridLayout gridLayout = new GridLayout();
		gridLayout.setRows(4);
		entUsuario.setLayout(gridLayout);
		entUsuario.add(rotulo);
		entUsuario.add(rotulo2);
		entUsuario.add(rotulo3);
		entUsuario.add(text);
		JOptionPane.showMessageDialog(null, entUsuario, "Atenção!",
				JOptionPane.INFORMATION_MESSAGE);

		ret = text.getText();
		return ret;
	}

	public static String jOptionPaneJComboBox(String[] dados, String msg) {
		String ret = "";
		JComboBox combo = new JComboBox();
		for (int i = 0; i < dados.length; i++) {
			combo.addItem(dados[i]);
		}
		
		// Cria um rótulo para o campo
		JLabel rotulo = new JLabel(msg);
		// Coloca o rótulo e a caixa de entrada numa JPanel:
		JPanel entUsuario = new JPanel();
		entUsuario.add(rotulo);
		entUsuario.add(combo);
		JOptionPane.showMessageDialog(null, entUsuario, "Atenção!",
				JOptionPane.INFORMATION_MESSAGE);
		ret = combo.getSelectedItem().toString();
		return ret;
	}
	public static String jOptionPaneJComboBox(String[] dados, String msg, String select) {
		String ret = "";
		JComboBox combo = new JComboBox();
		for (int i = 0; i < dados.length; i++) {
			combo.addItem(dados[i]);
		}
		combo.setSelectedItem(select);
		// Cria um rótulo para o campo
		JLabel rotulo = new JLabel(msg);
		// Coloca o rótulo e a caixa de entrada numa JPanel:
		JPanel entUsuario = new JPanel();
		entUsuario.add(rotulo);
		entUsuario.add(combo);
		JOptionPane.showMessageDialog(null, entUsuario, "Atenção!",
				JOptionPane.INFORMATION_MESSAGE);
		ret = combo.getSelectedItem().toString();
		return ret;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
