package in.novopay.platform_ui.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class DBUtils extends JavaUtils  {
	protected Connection conn;
	protected Statement stmt;
	
	// private String codeId;
	private static String organizationId;

	public String getOrganizationId() {
		return DBUtils.organizationId;
	}

	public void setOrganizationId(String organizationId) {
		DBUtils.organizationId = organizationId;
	}

	public Connection createConnection(String dbSchemaName) throws ClassNotFoundException {

		String dbSchema = configProperties.get("dbUrl") + dbSchemaName;
		String jdbcDriver = configProperties.get("jdbcDriver");
		try {
			if ((null == conn) || (!conn.getCatalog().equalsIgnoreCase(dbSchemaName))) {
				Class.forName(jdbcDriver);
				conn = DriverManager.getConnection(dbSchema, configProperties.get("dbUserName"),
						configProperties.get("dbPassword"));
				stmt = conn.createStatement();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public Connection createConnection3(String dbSchemaName) throws ClassNotFoundException {

		String dbSchema = configProperties.get("dbUrl3.0") + dbSchemaName;
		String jdbcDriver = configProperties.get("jdbcDriver");
		try {
			if ((null == conn) || (!conn.getCatalog().equalsIgnoreCase(dbSchemaName))) {
				Class.forName(jdbcDriver);
				conn = DriverManager.getConnection(dbSchema, configProperties.get("dbUserName3.0"),
						configProperties.get("dbPassword3.0"));
				stmt = conn.createStatement();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public void closeConnection(Connection conn) {

		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println("Unable to close the connection due to below error..!");
			e.printStackTrace();
		}
	}

	public String getPanNumber(String mobileNum) throws ClassNotFoundException {
		try {
			conn = createConnection("master");
			String query = "SELECT attr_value FROM master.organization_attribute "
					+ "WHERE attr_key='PAN' AND orgnization_id = (SELECT u.`organization` FROM `master`.`user` AS u "
					+ "JOIN `master`.`user_attribute` AS ua ON u.`id` = ua.`user_id` "
					+ "JOIN `master`.organization o ON u.organization = o.id WHERE ua.`attr_value`='" + mobileNum
					+ "' ORDER BY u.id DESC LIMIT 1);";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();

		}
		return null;
	}

	public String getBank(String beneIFSC) throws ClassNotFoundException {
		try {
			conn = createConnection("np_master");
			String query = "SELECT bank FROM np_master.`ifsc_master_new` WHERE ifsc_code = '" + beneIFSC + "';";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();

		}
		return null;
	}

	public String updateRemitterName(String remitter) throws ClassNotFoundException {
		try {
			conn = createConnection("rblsimulator");
			String query = "UPDATE `rblsimulator`.`beneficiary` SET `name` = 'John' WHERE remitterid = (SELECT remitterid FROM `rblsimulator`.`remitter` WHERE mobile = '"
					+ remitter + "');";
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();

		}
		return null;
	}

	public String deleteRemitterName(String remitter) throws ClassNotFoundException {
		try {
			conn = createConnection("rblsimulator");
			String query = "UPDATE `rblsimulator`.`beneficiary` SET `name` = '' WHERE remitterid = (SELECT remitterid FROM `rblsimulator`.`remitter` WHERE mobile = '"
					+ remitter + "');";
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();

		}
		return null;
	}

	public String deleteBeneFromRBLSimulator(String remitter) throws ClassNotFoundException {
		try {
			conn = createConnection("rblsimulator");
			String query = "DELETE FROM `rblsimulator`.`beneficiary` WHERE remitterid = (SELECT remitterid FROM `rblsimulator`.`remitter` WHERE mobile = '"
					+ remitter + "');";
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();

		}
		return null;
	}

	public String getRemittanceCharges(String amount, String category, String partner) throws ClassNotFoundException {
		try {
			conn = createConnection("limit_charges");
			String code = "";
			if (partner.equalsIgnoreCase("AXIS")) {
				code = "AXIS_REMIT_IMPS_CUSTOMER_CHARGE_ACTUAL";
			} else if (partner.equalsIgnoreCase("RBL")) {
				code = "RBL_REMIT_IMPS_CUSTOMER_CHARGE_DISPLAY";
			} else if (partner.equalsIgnoreCase("FINO")) {
				code = "FINO_REMIT_IMPS_CUSTOMER_CHARGE_DISPLAY";
			} else if (partner.equalsIgnoreCase("YBL")) {
				code = "YBL_REMIT_IMPS_CUSTOMER_CHARGE_DISPLAY";
			}
			if (Integer.parseInt(category) == 4) {
				code = code + "_KRO";
			}
			String query = "SELECT IF(" + amount + "*`percentage`/10000>`min_charge`/100, ROUND(" + amount
					+ "*`percentage`/10000,2), ROUND(`min_charge`/100,2)) charge FROM `limit_charges`.`charge_category_slabs` "
					+ "WHERE `category_code`='" + code + "' AND " + amount
					+ " BETWEEN `slab_from_amount`/100+1 AND `slab_to_amount`/100;";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();

		}
		return null;
	}

	public String getSettlementNeftConfig() throws ClassNotFoundException {
		try {
			conn = createConnection("limit_charges");

			String query = "SELECT prop_value FROM `config`.`configuration` WHERE prop_key = 'enable.rbl.neft.settlement.api'";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();

		}
		return null;
	}

	public String getOnDemandSettlementCharges(String mode, String partner, String amount)
			throws ClassNotFoundException {
		try {
			conn = createConnection("limit_charges");
			String code = "";
			if (mode.equalsIgnoreCase("NEFT") && partner.equalsIgnoreCase("RBL")
					&& getSettlementNeftConfig().equals("NO")) {
				code = "MRCHNT_ON_DEMAND_CASHOUT_BANK_SETTLEMENT_DEFAULT_CHRG";
			} else if (mode.equalsIgnoreCase("NEFT") && partner.equalsIgnoreCase("RBL")
					&& getSettlementNeftConfig().equals("YES")) {
				code = "MRCHNT_ON_DEMAND_CASHOUT_BANK_SETTLEMENT_RBL_NEFT_API_CHRG";
			} else if (mode.equalsIgnoreCase("IMPS") && partner.equalsIgnoreCase("RBL")) {
				code = "MRCHNT_ON_DEMAND_CASHOUT_BANK_SETTLEMENT_DEFAULT_CHRG_IMPS";
			} else if (mode.equalsIgnoreCase("NEFT") && partner.equalsIgnoreCase("YBL")) {
				code = "MRCHNT_ON_DEMAND_CASHOUT_YBL_BANK_NEFT_SETTLEMENT_AMOUNT";
			} else if (mode.equalsIgnoreCase("IMPS") && partner.equalsIgnoreCase("YBL")) {
				code = "MRCHNT_ON_DEMAND_CASHOUT_YBL_BANK_IMPS_SETTLEMENT_DEFAULT_CHRG_IMPS";
			} else if (mode.equalsIgnoreCase("NEFT") && partner.equalsIgnoreCase("RAZORPAY")) {
				code = "MRCHNT_ON_DEMAND_CASHOUT_RAZORPAY_NEFT_SETTLEMENT_DEFAULT_CHRG_NEFT";
			} else if (mode.equalsIgnoreCase("IMPS") && partner.equalsIgnoreCase("RAZORPAY")) {
				code = "MRCHNT_ON_DEMAND_CASHOUT_RAZORPAY_IMPS_SETTLEMENT_DEFAULT_CHRG_IMPS";
			}
			String query = "SELECT ROUND(`base_charge`/100,2) FROM `limit_charges`.`charge_category_slabs` "
					+ "WHERE `category_code`='" + code + "' AND " + amount
					+ " BETWEEN `slab_from_amount`/100+1 AND `slab_to_amount`/100;";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();

		}
		return null;
	}

	public String getBillPaymentCharges(String vendor) throws ClassNotFoundException {
		try {
			conn = createConnection("limit_charges");
			String code = "";
			if (vendor.equalsIgnoreCase("Cyberplat")) {
				code = "CP_ELECTRICITY_BILLPAY_CHARGE_DISPLAY";
			} else if (vendor.equalsIgnoreCase("Billavenue")) {
				code = "BILL_AVENUE_ELECTRICITY_BILLPAY_CHARGE_DISPLAY";
			}
			String query = "SELECT ROUND(`base_charge`/100,2) FROM `limit_charges`.`charge_category_slabs` "
					+ "WHERE `category_code`='" + code + "'";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();

		}
		return null;
	}

	public String getRechargeChargesAndComm(String chargeType, String vendor, String type, String operator)
			throws ClassNotFoundException {
		try {
			conn = createConnection("limit_charges");
			String code = "", codePrefix = "";
			if (chargeType.equalsIgnoreCase("charges")) {
				codePrefix = "CHARGE_DISPLAY";
			} else if (chargeType.equalsIgnoreCase("comm")) {
				codePrefix = "COMM_RET";
			}
			if (vendor.equalsIgnoreCase("Gorecharge")) {
				switch (type) {
				case "Prepaid":
					switch (operator) {
					case "AIRTEL":
						code = "GORECHARGE_BILLPAY_PREPAID_AIRTEL_" + codePrefix;
						break;
					case "BSNL":
						code = "GORECHARGE_BILLPAY_PREPAID_BSNL_" + codePrefix;
						break;
					}
					break;
				case "Postpaid":
					code = "GORECHARGE_BILLPAY_POSTPAID_AIRTEL_" + codePrefix;
					break;
				case "DTH":
					code = "GORECHARGE_BILLPAY_DTH_AIRTEL_" + codePrefix;
					break;
				case "Datacard":
					code = "GORECHARGE_BILLPAY_DATACARD_AIRTEL_" + codePrefix;
					break;
				}
			} else if (vendor.equalsIgnoreCase("Multilink")) {
				switch (type) {
				case "Prepaid":
					switch (operator) {
					case "AIRTEL":
						code = "MULTILINK_BILLPAY_PREPAID_RA_" + codePrefix;
						break;
					case "Tata DOCOMO":
						code = "MULTILINK_BILLPAY_PREPAID_RD_" + codePrefix;
						break;
					}
					break;
				case "Postpaid":
					code = "MULTILINK_BILLPAY_POSTPAID_RA_" + codePrefix;
					break;
				case "DTH":
					code = "MULTILINK_BILLPAY_DTH_DA_" + codePrefix;
					break;
				case "Datacard":
					code = "MULTILINK_BILLPAY_DATACARD_RA_" + codePrefix;
					break;
				}
			}
			String chargeQuery = "SELECT ROUND(`base_charge`/100,2) FROM `limit_charges`.`charge_category_slabs` "
					+ "WHERE `category_code`='" + code + "'";
			String commQuery = "SELECT ROUND(`percentage`/100,2) FROM `limit_charges`.`charge_category_slabs` "
					+ "WHERE `category_code`='" + code + "'";
			stmt = conn.createStatement();
			ResultSet rs = null;
			if (chargeType.equalsIgnoreCase("charges")) {
				rs = stmt.executeQuery(chargeQuery);
			} else if (chargeType.equalsIgnoreCase("comm")) {
				rs = stmt.executeQuery(commQuery);
			}
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();

		}
		return null;
	}

	public String getRemittanceComm(double amount, String code, int category) throws ClassNotFoundException {
		try {
			conn = createConnection("limit_charges");
			if (category == 4) {
				code = code + "_KRO";
			}
			String query = "SELECT IF(" + amount + "*`percentage`/10000>`min_charge`/100, ROUND(" + amount
					+ "*`percentage`/10000,2), ROUND(`min_charge`/100,2)) comm FROM `limit_charges`.`charge_category_slabs` "
					+ "WHERE `category_code`='" + code + "_REMIT_IMPS_AGENT_COMM' AND " + amount
					+ " BETWEEN `slab_from_amount`/100+1 AND `slab_to_amount`/100 AND `min_charge` > 0";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();

		}
		return null;
	}

	public String getAepsComm(String amount, String txnType, String partner) throws ClassNotFoundException {
		try {
			conn = createConnection("limit_charges");
			String code = "";
			if (txnType.equalsIgnoreCase("Deposit")) {
				code = partner + "_AEPS_DEPOSIT_AGENT_COMM";
			} else if (txnType.equalsIgnoreCase("Withdrawal")) {
				code = partner + "_AEPS_WITHDRAWAL_AGENT_COMM";
			}
			String query = "SELECT IF(" + amount + " < 200, 0.00, (SELECT `base_charge`/100 + IF(" + amount
					+ "*`percentage`/10000<`max_charge`/100, ROUND(" + amount
					+ "*`percentage`/10000,2), ROUND(`max_charge`/100,2)) comm FROM `limit_charges`.`charge_category_slabs` "
					+ "WHERE `category_code`='" + code + "' AND " + amount
					+ " BETWEEN `slab_from_amount`/100+1 AND `slab_to_amount`/100)) comm FROM `limit_charges`.`charge_category_slabs` LIMIT 1;";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();

		}
		return null;
	}

	public String getWalletBalance(String mobNum, String wallet) throws ClassNotFoundException {
		try {
			conn = createConnection("wallet");
			String query = "";
			if (wallet.equalsIgnoreCase("retailer")) {
				query = "SELECT `account_balance_derived` FROM `wallet`.`m_savings_account` WHERE `account_no` = (SELECT attr_value FROM `master`.`organization_attribute` oa WHERE `attr_key` = 'WALLET_ACCOUNT_NUMBER' AND `orgnization_id` = (SELECT `organization` FROM `master`.`user` WHERE `id` = (SELECT `user_id` FROM `master`.`user_attribute` WHERE `attr_value` = '"
						+ mobNum + "' ORDER BY id DESC LIMIT 1)));";
			} else if (wallet.equalsIgnoreCase("cashout")) {
				query = "SELECT `account_balance_derived` FROM `wallet`.`m_savings_account` WHERE `account_no` = (SELECT attr_value FROM `master`.`organization_attribute` oa WHERE `attr_key` = 'CASH_OUT_WALLET_ACCOUNT_NUMBER' AND `orgnization_id` = (SELECT `organization` FROM `master`.`user` WHERE `id` = (SELECT `user_id` FROM `master`.`user_attribute` WHERE `attr_value` = '"
						+ mobNum + "' ORDER BY id DESC LIMIT 1)));";
			} else {
				query = "SELECT `account_balance_derived` FROM `wallet`.`m_savings_account` WHERE `account_no` = (SELECT attr_value FROM `master`.`organization_attribute` oa WHERE `attr_key` = 'OTC_MERCHANT_WALLET_ACCOUNT_NUMBER' AND `orgnization_id` = (SELECT `organization` FROM `master`.`user` WHERE `id` = (SELECT `user_id` FROM `master`.`user_attribute` WHERE `attr_value` = '"
						+ mobNum + "' ORDER BY id DESC LIMIT 1)));";
			}
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();

		}
		return null;
	}

	public String updateWalletBalance(String mobNum, String wallet, String amount) throws ClassNotFoundException {
		try {
			conn = createConnection("wallet");
			String query = "";
			if (wallet.equalsIgnoreCase("retailer")) {
				query = "UPDATE `wallet`.`m_savings_account` SET `account_balance_derived` = '" + amount
						+ "' WHERE `account_no` = (SELECT attr_value FROM `master`.`organization_attribute` oa WHERE `attr_key` = 'WALLET_ACCOUNT_NUMBER' AND `orgnization_id` = (SELECT `organization` FROM `master`.`user` WHERE `id` = (SELECT `user_id` FROM `master`.`user_attribute` WHERE `attr_value` = '"
						+ mobNum + "' ORDER BY id DESC LIMIT 1)));";
			} else if (wallet.equalsIgnoreCase("cashout")) {
				query = "UPDATE `wallet`.`m_savings_account` SET `account_balance_derived` = '" + amount
						+ "' WHERE `account_no` = (SELECT attr_value FROM `master`.`organization_attribute` oa WHERE `attr_key` = 'CASH_OUT_WALLET_ACCOUNT_NUMBER' AND `orgnization_id` = (SELECT `organization` FROM `master`.`user` WHERE `id` = (SELECT `user_id` FROM `master`.`user_attribute` WHERE `attr_value` = '"
						+ mobNum + "' ORDER BY id DESC LIMIT 1)));";
			} else if (wallet.equalsIgnoreCase("merchant")) {
				query = "UPDATE `wallet`.`m_savings_account` SET `account_balance_derived` = '" + amount
						+ "' WHERE `account_no` = (SELECT attr_value FROM `master`.`organization_attribute` oa WHERE `attr_key` = 'OTC_MERCHANT_WALLET_ACCOUNT_NUMBER' AND `orgnization_id` = (SELECT `organization` FROM `master`.`user` WHERE `id` = (SELECT `user_id` FROM `master`.`user_attribute` WHERE `attr_value` = '"
						+ mobNum + "' ORDER BY id DESC LIMIT 1)));";
			}
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();

		}
		return null;
	}

	public String getBeneAmount(String partner) throws ClassNotFoundException {
		try {
			conn = createConnection("config");
			String query = "";
			if (partner.equalsIgnoreCase("RBL")) {
				query = "SELECT prop_value FROM config.configuration WHERE prop_key = 'rbl.bene.val.amount';";
			} else if (partner.equalsIgnoreCase("AXIS")) {
				query = "SELECT prop_value FROM config.configuration WHERE prop_key = 'axis.bene.val.amount';";
			} else if (partner.equalsIgnoreCase("YBL")) {
				query = "SELECT prop_value FROM config.configuration WHERE prop_key = 'ybl.bene.val.amount';";
			} else if (partner.equalsIgnoreCase("FINO")) {
				query = "SELECT prop_value FROM config.configuration WHERE prop_key = 'fino.bene.val.amount';";
			}
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();

		}
		return null;
	}

	public String updateQueuingConfig(String threshold, String partner, String code) throws ClassNotFoundException {
		try {
			conn = createConnection("np_remittance");
			String query = "UPDATE np_remittance.queuing_config SET `threshold_error_count` = '" + threshold
					+ "' WHERE partner = '" + partner + "' AND error_code = '" + code + "';";
			stmt = conn.createStatement();
			stmt.executeUpdate(query);

		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();

		}
		return null;
	}

	public List<String> getBankCodeToUnqueue(String partner) throws ClassNotFoundException {
		try {
			conn = createConnection("config");
			String query = "SELECT bank_code FROM `np_master`.`bank_master` WHERE bank_name "
					+ "IN (SELECT bank_name FROM `np_remittance`.`queued_banks` WHERE partner = '" + partner
					+ "' AND end_time IS NULL)";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			List<String> list = new ArrayList<String>();
			while (rs.next()) {
				list.add(rs.getString(1));
			}
			return list;
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();

		}
		return null;
	}

	public String verifyIfQueuingIsEnabled(String partner) throws ClassNotFoundException {
		try {
			conn = createConnection("np_remittance");
			String query = "SELECT end_time FROM `np_remittance`.`queued_banks` where partner = '" + partner
					+ "' ORDER BY id DESC LIMIT 1";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();

		}
		return null;
	}

	public String verifyIfTxnIsQueued(String partner) throws ClassNotFoundException {
		try {
			conn = createConnection("np_remittance");
			String query = "SELECT IF(STRCMP((SELECT payment_ref_code FROM `np_remittance`.`queued_remittance` where partner = '"
					+ partner + "' ORDER BY id DESC LIMIT 1),"
					+ "(SELECT payment_ref_code FROM `np_remittance`.`remittance_outward_table` where partner = '"
					+ partner + "' ORDER BY id DESC LIMIT 1)) = 0, 'Queued', 'NotQueued') Txn;";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();

		}
		return null;
	}

	public String getQueuedBankName() throws ClassNotFoundException {
		try {
			conn = createConnection("np_remittance");
			String query = "SELECT bank_name FROM `np_remittance`.`queued_banks` WHERE end_time IS NULL ORDER BY id DESC LIMIT 1";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();

		}
		return null;
	}

	public String deleteRecordsFromFRC(String code, String partner) throws ClassNotFoundException {
		try {
			conn = createConnection("np_remittance");
			String query = "";
			query = "DELETE FROM `np_remittance`.`failed_remittance_code` WHERE error_code = '" + code
					+ "' AND partner = '" + partner + "';";
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();

		}
		return null;
	}

	public void updateRBLTransactionStatus(String bankRefCode, String paymentStatus) throws ClassNotFoundException {
		try {
			String sql = "UPDATE `transaction` SET `paymentstatus`='" + paymentStatus + "', `isrefundfile`='YES' "
					+ "WHERE `channelpartnerrefno`='" + bankRefCode + "';";
			conn = createConnection("rblsimulator");
			stmt = conn.createStatement();
			stmt.execute(sql);
			System.out.println(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateRemittanceOutwardStatus(String paymentRefCode, String status, String refundStatus)
			throws ClassNotFoundException {
		try {
			String sql = "UPDATE `np_remittance`.`remittance_outward_table` SET `status` = '" + status
					+ "', `refund_status` = '" + refundStatus + "' WHERE `payment_ref_code` = '" + paymentRefCode + "'";
			conn = createConnection("rblsimulator");
			stmt = conn.createStatement();
			stmt.execute(sql);
			System.out.println(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateBatchStatus(String batchName, String status) throws ClassNotFoundException {
		try {
			String sql = "UPDATE batch_master SET `last_run_status` = '" + status + "' WHERE job_name ='" + batchName
					+ "';";
			conn = createConnection("np_ops");
			stmt = conn.createStatement();
			stmt.execute(sql);
			System.out.println("Updating status of " + batchName + " to " + status);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String selectPaymentRefCode() throws ClassNotFoundException {
		try {
			String query = "SELECT payment_ref_code FROM `np_remittance`.`remittance_outward_table` ORDER BY id DESC LIMIT 1;";
			conn = createConnection("np_remittance");
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void updateRBLTxnStatus(String paymentRefCode) throws ClassNotFoundException {
		try {
			String sql = "UPDATE `np_remittance`.`remittance_outward_table` SET `status`='UNKNOWN' WHERE payment_ref_code = '"
					+ paymentRefCode + "';";
			conn = createConnection("np_remittance");
			stmt = conn.createStatement();
			stmt.execute(sql);
			System.out.println("Updating status of txn with ref num " + paymentRefCode + " to UNKNOWN");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateAxisTransactionStatus(String bankRefCode) throws ClassNotFoundException {
		try {
			String sql = "UPDATE `remittance_outward_table` SET `status`='FAIL' WHERE `payment_ref_code`='"
					+ bankRefCode + "';";
			conn = createConnection("np_remittance");
			stmt = conn.createStatement();
			stmt.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String[] getPaymentStatusAndRRNNo(String paymentRefCode) throws ClassNotFoundException {
		try {
			String query = "SELECT `status`,`bank_ref_code` FROM `remittance_outward_table` WHERE `payment_ref_code`="
					+ paymentRefCode;
			conn = createConnection("np_remittance");
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			return new String[] { rs.getString("status"), rs.getString("bank_ref_code") };
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getPAN(String mobNum) throws ClassNotFoundException {
		try {
			conn = createConnection("master");
			String query = "SELECT attr_value FROM master.`organization_attribute` WHERE attr_key = 'PAN' AND `orgnization_id` = (SELECT organization FROM master.user WHERE id = (SELECT user_id FROM master.user_attribute WHERE attr_value = '"
					+ mobNum + "' ORDER BY id DESC LIMIT 1))";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getTDSPercentage(String mobNum) throws ClassNotFoundException {
		try {
			conn = createConnection("config");
			String query = "";
			if (getPAN(mobNum) != null) {
				query = "SELECT prop_value FROM `config`.`configuration` WHERE prop_key = 'novopay.agent.tds.percentage.with.pan'";
			} else {
				query = "SELECT prop_value FROM `config`.`configuration` WHERE prop_key = 'novopay.agent.tds.percentage.without.pan'";
			}
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String modeOfTransfer(String ifscCode) throws ClassNotFoundException {
		try {
			conn = createConnection("np_master");
			String query = "SELECT imps_supported FROM np_master.ifsc_master_new WHERE ifsc_code = '" + ifscCode + "'";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String ifscCodeDetails(String ifscCode, String key) throws ClassNotFoundException {
		try {
			conn = createConnection("np_master");
			String query = "SELECT bank, state, district, branch FROM `np_master`.`ifsc_master_new` WHERE ifsc_code = '"
					+ ifscCode + "';";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			return rs.getString(key);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<String> getListOfRetailerMobNum() throws ClassNotFoundException {
		try {
			conn = createConnection("np_sales");
			String query = "SELECT ua.attr_value, u.status FROM master.user u "
					+ "JOIN master.user_attribute ua ON u.id = ua.user_id WHERE ua.attr_key = 'MSISDN'";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			List<String> list = new ArrayList<String>();
			while (rs.next()) {
				list.add(rs.getString(1));
			}
			return list;
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();

		}
		return null;
	}

	public String getLimitRemaining(String mobNum, String partner) throws ClassNotFoundException {
		try {
			conn = createConnection("np_master");
			Date date = new Date();
			LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			int currentMonth = localDate.getMonthValue();
			String month = "";
			if (currentMonth >= 10) {
				month = Integer.toString(currentMonth);
			} else {
				month = "0" + Integer.toString(currentMonth);
			}
			String query = "SELECT (SELECT `monthly_amount_limit` FROM `limit_charges`.`limit_category_master` "
					+ "WHERE `category_code` = 'LIMITS_REMITTER_" + partner + "_NO_KYC')-(SELECT IF((SELECT "
					+ "SUM(txn_amount) FROM `limit_charges`.`entity_consumed_limits` "
					+ "WHERE category = 'LIMITS_REMITTER_" + partner + "_NO_KYC' AND entity_id = '" + mobNum
					+ "' AND is_reversed = '0' AND txn_date LIKE '2021-" + month
					+ "-%') IS NULL, 0, (SELECT SUM(txn_amount) FROM `limit_charges`.`entity_consumed_limits` "
					+ "WHERE category = 'LIMITS_REMITTER_" + partner + "_NO_KYC' AND entity_id = '" + mobNum
					+ "' AND is_reversed = '0' AND txn_date LIKE '2021-" + month + "-%')))";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			return rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void updateBlackoutDuration(String duration) throws ClassNotFoundException {
		try {
			String sql = "UPDATE `service_repo`.`transaction_blackout_config` SET `blackout_duration` = '" + duration
					+ "' WHERE `api_name` = 'moneyTransferBatch' AND `channel_id` = 'WEBAPP';";
			conn = createConnection("np_remittance");
			stmt = conn.createStatement();
			stmt.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getChargeCategory(String mobNum) throws ClassNotFoundException {
		try {
			conn = createConnection("master");
			String query = "SELECT chrge_category FROM master.organization "
					+ "WHERE id = (SELECT u.`organization` FROM `master`.`user` AS u "
					+ "JOIN `master`.`user_attribute` AS ua ON u.`id`=ua.`user_id` WHERE ua.`attr_value`='" + mobNum
					+ "' ORDER BY u.id DESC LIMIT 1);";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();

		}
		return null;
	}

	public String updateMPIN(String mobNum) throws ClassNotFoundException {
		try {
			conn = createConnection("master");
			String query = "UPDATE `master`.`user_auth_mechanism` "
					+ "SET `value` = '0ffe1abd1a08215353c233d6e009613e95eec4253832a761af28ff37ac5a150c' "
					+ "WHERE user_id = (SELECT user_id FROM master.user_attribute WHERE attr_value = '" + mobNum
					+ "' ORDER BY id DESC LIMIT 1);";
			System.out.println(query);
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB!! Update failed..!");
			sqe.printStackTrace();

		}
		return null;
	}

	public String sms() throws ClassNotFoundException {
		try {
			conn = createConnection("sms_log");
			String query = "SELECT message FROM sms_log.sms_log ORDER BY id DESC LIMIT 1;";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();
		}
		return null;
	}

	public String smsFt() throws ClassNotFoundException {
		try {
			conn = createConnection("sms_log");
			String query = "SELECT message FROM sms_log.sms_log ORDER BY id DESC LIMIT 1 OFFSET 1;";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();
		}
		return null;
	}

	public String smsNum1() throws ClassNotFoundException {
		try {
			conn = createConnection("sms_log");
			String query = "SELECT sent_to FROM sms_log.sms_log ORDER BY id DESC LIMIT 1;";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();
		}
		return null;
	}

	public String smsNum2() throws ClassNotFoundException {
		try {
			conn = createConnection("sms_log");
			String query = "SELECT sent_to FROM sms_log.sms_log ORDER BY id DESC LIMIT 1 OFFSET 1;";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();
		}
		return null;
	}

	public String beneAccountPAN() throws ClassNotFoundException {
		try {
			conn = createConnection("np_actor");
			String query = "SELECT handle_value FROM `np_actor`.`fin_inst_handle` ORDER BY id DESC LIMIT 1";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();
		}
		return null;
	}

	public String paymentRefCode(String partner) throws ClassNotFoundException {
		try {
			conn = createConnection("np_actor");
			String query = "";
			if (partner == "all") {
				query = "SELECT `payment_ref_code` FROM `np_remittance`.`remittance_outward_table` ORDER BY id DESC LIMIT 1;";
			} else {
				query = "SELECT `payment_ref_code` FROM `np_remittance`.`remittance_outward_table` where partner = '"
						+ partner + "' ORDER BY id DESC LIMIT 1;";
			}
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();
		}
		return null;
	}

	public String verifyIfQueuingIsDisabled(Map<String, String> usrData, String partner) throws ClassNotFoundException {
		try {
			conn = createConnection("np_actor");
			String query = "SELECT end_remarks FROM `np_remittance`.`queued_banks` WHERE partner = '" + partner
					+ "' AND bank_name = '" + getBank(usrData.get("BENEIFSC")) + "' ORDER BY id DESC LIMIT 1;";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();
		}
		return null;
	}

	public String getPaymentRefCode(String partner) throws ClassNotFoundException {
		try {
			conn = createConnection("np_actor");
			String query = "SELECT payment_ref_code FROM `np_remittance`.`queued_remittance` where partner = '"
					+ partner + "' ORDER BY id DESC LIMIT 1";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();
		}
		return null;
	}

	public String getBankRefCode(String partner) throws ClassNotFoundException {
		try {
			conn = createConnection("np_actor");

			String query = "SELECT bank_ref_code FROM `np_remittance`.`remittance_outward_table` WHERE partner = '"
					+ partner + "' ORDER BY id DESC LIMIT 1";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();
		}
		return null;
	}

	public String getBankRefCodePS(String partner) throws ClassNotFoundException {
		try {
			conn = createConnection("np_actor");

			String query = "SELECT bank_ref_code FROM `np_remittance`.`remittance_outward_table` WHERE partner = '"
					+ partner + "' ORDER BY id DESC LIMIT 1 OFFSET 1";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();
		}
		return null;
	}

	public List<String[]> queuedTxnReport(String mobNum, int limit) throws ClassNotFoundException {
		try {
			conn = createConnection("np_actor");
			String query = "SELECT DATE_FORMAT(rot.created_on, '%e %b %Y') Txn_Date, DATE_FORMAT(rot.created_on, "
					+ "'%e %b %Y, %r') `Txn_Time_Initiated`, DATE_FORMAT(rot.processed_on, '%e %b %Y, %r') "
					+ "`Txn_Time_Processed`,IF(STRCMP(rot.`remittance_type`,'C2A') = 0,'REMITTANCE','F') "
					+ "Txn_Type,rot.`remitter_msisdn` `Remitter_Mobile#`,rot.`payment_ref_code` Txn_Id,"
					+ "CONCAT(LEFT(rot.amount, LENGTH(rot.amount)-2), '.', RIGHT(rot.amount, 2)) "
					+ "Txn_Amount,rot.`beneficiary_bank` Bene_Bank,rot.`status` `Status` FROM "
					+ "np_remittance.remittance_outward_table rot JOIN np_remittance.queued_remittance qr "
					+ "ON rot.payment_ref_code = qr.`payment_ref_code` WHERE rot.`csp_code` = (SELECT o.code "
					+ "FROM `master`.`user` AS u JOIN `master`.`user_attribute` AS ua ON u.`id`=ua.`user_id` "
					+ "JOIN `master`.organization o ON u.organization = o.id WHERE ua.`attr_value`='" + mobNum
					+ "' ORDER BY u.id DESC LIMIT 1) AND qr.`created_on`>=DATE_ADD(CURDATE(), INTERVAL -1 DAY) "
					+ "ORDER BY rot.id DESC LIMIT " + limit + ";";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			int nCol = rs.getMetaData().getColumnCount();
			List<String[]> list = new ArrayList<String[]>();
			while (rs.next()) {
				String[] row = new String[nCol];
				for (int iCol = 1; iCol <= nCol; iCol++) {
					Object obj = rs.getObject(iCol);
					row[iCol - 1] = (obj == null) ? "NA" : obj.toString();
				}
				list.add(row);
			}
			return list;
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();
		}
		return null;
	}

	public List<String[]> queuedBankReport() throws ClassNotFoundException {
		try {
			conn = createConnection("np_actor");
			String query = "SELECT bank_name, partner, DATE_FORMAT(start_time, '%e %b %Y, %r') start_time, "
					+ "IF(end_time IS NULL, 'INQUEUE', DATE_FORMAT(end_time, '%e %b %Y, %r')) end_time, "
					+ "DATE_FORMAT(TIMEDIFF(end_time, start_time), '%Hh %im %ss') duration "
					+ "FROM np_remittance.queued_banks WHERE partner = 'RBL' AND start_time >= "
					+ "DATE_ADD(CURDATE(), INTERVAL -1 DAY) LIMIT 1";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			int nCol = rs.getMetaData().getColumnCount();
			List<String[]> list = new ArrayList<String[]>();
			while (rs.next()) {
				String[] row = new String[nCol];
				for (int iCol = 1; iCol <= nCol; iCol++) {
					Object obj = rs.getObject(iCol);
					row[iCol - 1] = (obj == null) ? "NA" : obj.toString();
				}
				list.add(row);
			}
			return list;
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();
		}
		return null;
	}

	public List<String[]> timeoutReport(String mobNum, String status) throws ClassNotFoundException {
		try {
			conn = createConnection("np_actor");
			String query = "SELECT DATE_FORMAT(created_on, '%e %b %Y, %r') `txn_time_initiated`, "
					+ "IF(`remittance_type`='C2A','REMITTANCE',`remittance_type`) txn_type,`remitter_msisdn`,"
					+ "`beneficiary_name`,`beneficiary_bank`,`payment_ref_code`,CONCAT(LEFT(amount, "
					+ "LENGTH(amount)-2), '.', RIGHT(amount, 2)) amount,`partner`,`status` FROM "
					+ "np_remittance.remittance_outward_table WHERE `csp_code` = (SELECT o.code "
					+ "FROM `master`.`user` AS u JOIN `master`.`user_attribute` AS ua ON u.`id`=ua.`user_id` "
					+ "JOIN `master`.organization o ON u.organization = o.id WHERE ua.`attr_value`='" + mobNum
					+ "' ORDER BY u.id DESC LIMIT 1) AND `status` = '" + status + "' AND (`npci_response_code` = '91' "
					+ "OR `npci_response_code` IS NULL) ORDER BY id DESC LIMIT 1";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			int nCol = rs.getMetaData().getColumnCount();
			List<String[]> list = new ArrayList<String[]>();
			while (rs.next()) {
				String[] row = new String[nCol];
				for (int iCol = 1; iCol <= nCol; iCol++) {
					Object obj = rs.getObject(iCol);
					row[iCol - 1] = (obj == null) ? "NA" : obj.toString();
				}
				list.add(row);
			}
			return list;
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();
		}
		return null;
	}

	public List<String[]> refundReport(String mobNum) throws ClassNotFoundException {
		try {
			conn = createConnection("np_actor");
			String query = "SELECT CONCAT(DATE_FORMAT(created_on, '%e %b %Y, '), UPPER(DATE_FORMAT(created_on, '%h:%i:%s %p'))) date, "
					+ "IF(`remittance_type`='C2A','REMITTANCE',`remittance_type`) txn_type, "
					+ "`payment_ref_code`, `remitter_msisdn`,`beneficiary_name`,`beneficiary_bank`,`beneficiary_ifsc_code`,"
					+ "`beneficiary_acc_num`,CONCAT(LEFT(amount, LENGTH(amount)-2), '.', RIGHT(amount, 2)) amount,"
					+ "CONCAT(LEFT(charge, LENGTH(charge)-2), '.', RIGHT(charge, 2)) charge, "
					+ "IF(`refund_completed_on` IS NULL, 'TO_BE_REFUNDED', "
					+ "CONCAT(DAY(DATE(refund_completed_on)),' ',SUBSTRING(MONTHNAME(DATE(refund_completed_on)),1,3),' ',YEAR(DATE(refund_completed_on)))) refunded_date "
					+ "FROM np_remittance.remittance_outward_table "
					+ "WHERE `csp_code` = (SELECT o.code FROM `master`.`user` AS u JOIN `master`.`user_attribute` AS ua "
					+ "ON u.`id`=ua.`user_id` JOIN `master`.organization o ON u.organization = o.id "
					+ "WHERE ua.`attr_value`='" + mobNum + "' ORDER BY u.id DESC LIMIT 1) "
					+ "AND refund_status IS NOT NULL ORDER BY id DESC LIMIT 1";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			int nCol = rs.getMetaData().getColumnCount();
			List<String[]> list = new ArrayList<String[]>();
			while (rs.next()) {
				String[] row = new String[nCol];
				for (int iCol = 1; iCol <= nCol; iCol++) {
					Object obj = rs.getObject(iCol);
					row[iCol - 1] = (obj == null) ? "NA" : obj.toString();
				}
				list.add(row);
			}
			return list;
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();
		}
		return null;
	}

	public List<String[]> accountStatementMT(String mobNum) throws ClassNotFoundException {
		try {
			conn = createConnection("np_actor");

			String query = "SELECT DATE_FORMAT(created_date, '%d-%m-%Y') txn_date, DATE_FORMAT(created_date, '%H:%i') txn_time, "
					+ "(SELECT SUBSTR(RIGHT(`comment`, 12),1,11) FROM wallet.`m_savings_account_transaction` "
					+ "ORDER BY id DESC LIMIT 1 OFFSET 3) ref_no, (SELECT SUBSTR(RIGHT(`comment`, 23),1,10) "
					+ "FROM wallet.`m_savings_account_transaction` ORDER BY id DESC LIMIT 1 OFFSET 3) msisdn, "
					+ "(SELECT SUBSTR(`comment`,1,LENGTH(`comment`)-25) FROM wallet.`m_savings_account_transaction` "
					+ "ORDER BY id DESC LIMIT 1 OFFSET 3) description, "
					+ "(SELECT CONCAT('-',SUBSTR(amount,1,LENGTH(`amount`)-4)) "
					+ "FROM wallet.`m_savings_account_transaction` ORDER BY id DESC LIMIT 1 OFFSET 3) amount, "
					+ "(SELECT CONCAT('-',SUBSTR(amount,1,LENGTH(`amount`)-4)) "
					+ "FROM wallet.`m_savings_account_transaction` ORDER BY id DESC LIMIT 1 OFFSET 2) charge, "
					+ "(SELECT SUBSTR(amount,1,LENGTH(`amount`)-4) "
					+ "FROM wallet.`m_savings_account_transaction` ORDER BY id DESC LIMIT 1) comm, "
					+ "(SELECT CONCAT('-',SUBSTR(amount,1,LENGTH(`amount`)-4)) FROM wallet.`m_savings_account_transaction` "
					+ "ORDER BY id DESC LIMIT 1 OFFSET 1) tds "
					+ "FROM wallet.`m_savings_account_transaction` WHERE `savings_account_id` = "
					+ "(SELECT id FROM wallet.`m_savings_account` WHERE account_no = (SELECT attr_value "
					+ "FROM master.organization_attribute WHERE attr_key = 'WALLET_ACCOUNT_NUMBER' AND orgnization_id = "
					+ "(SELECT organization FROM master.user WHERE id = (SELECT user_id FROM master.user_attribute "
					+ "WHERE attr_value = '" + mobNum
					+ "' ORDER BY id DESC LIMIT 1)))) ORDER BY id DESC LIMIT 1 OFFSET 1;";

			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			int nCol = rs.getMetaData().getColumnCount();
			List<String[]> list = new ArrayList<String[]>();
			while (rs.next()) {
				String[] row = new String[nCol];
				for (int iCol = 1; iCol <= nCol; iCol++) {
					Object obj = rs.getObject(iCol);
					row[iCol - 1] = (obj == null) ? "NA" : obj.toString();
				}
				list.add(row);
			}
			return list;
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();
		}
		return null;
	}

	public List<String[]> accountStatementMTFt(String mobNum) throws ClassNotFoundException {
		try {
			conn = createConnection("np_actor");

			String query = "SELECT DATE_FORMAT(created_date, '%d-%m-%Y') txn_date, DATE_FORMAT(created_date, '%H:%i') txn_time, "
					+ "(SELECT SUBSTR(RIGHT(`comment`, 11),1,10) FROM wallet.`m_savings_account_transaction` "
					+ "ORDER BY id DESC LIMIT 1 OFFSET 3) ref_no, (SELECT SUBSTR(RIGHT(`comment`, 22),1,10) "
					+ "FROM wallet.`m_savings_account_transaction` ORDER BY id DESC LIMIT 1 OFFSET 3) msisdn, "
					+ "(SELECT SUBSTR(`comment`,1,LENGTH(`comment`)-24) FROM wallet.`m_savings_account_transaction` "
					+ "ORDER BY id DESC LIMIT 1 OFFSET 3) description, "
					+ "(SELECT CONCAT('-',SUBSTR(amount,1,LENGTH(`amount`)-4)) "
					+ "FROM wallet.`m_savings_account_transaction` ORDER BY id DESC LIMIT 1 OFFSET 3) amount, "
					+ "(SELECT CONCAT('-',SUBSTR(amount,1,LENGTH(`amount`)-4)) "
					+ "FROM wallet.`m_savings_account_transaction` ORDER BY id DESC LIMIT 1 OFFSET 2) charge, "
					+ "(SELECT SUBSTR(amount,1,LENGTH(`amount`)-4) "
					+ "FROM wallet.`m_savings_account_transaction` ORDER BY id DESC LIMIT 1) comm, "
					+ "(SELECT CONCAT('-',SUBSTR(amount,1,LENGTH(`amount`)-4)) FROM wallet.`m_savings_account_transaction` "
					+ "ORDER BY id DESC LIMIT 1 OFFSET 1) tds "
					+ "FROM wallet.`m_savings_account_transaction` WHERE `savings_account_id` = "
					+ "(SELECT id FROM wallet.`m_savings_account` WHERE account_no = (SELECT attr_value "
					+ "FROM master.organization_attribute WHERE attr_key = 'WALLET_ACCOUNT_NUMBER' AND orgnization_id = "
					+ "(SELECT organization FROM master.user WHERE id = (SELECT user_id FROM master.user_attribute "
					+ "WHERE attr_value = '" + mobNum
					+ "' ORDER BY id DESC LIMIT 1)))) ORDER BY id DESC LIMIT 1 OFFSET 1;";

			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			int nCol = rs.getMetaData().getColumnCount();
			List<String[]> list = new ArrayList<String[]>();
			while (rs.next()) {
				String[] row = new String[nCol];
				for (int iCol = 1; iCol <= nCol; iCol++) {
					Object obj = rs.getObject(iCol);
					row[iCol - 1] = (obj == null) ? "NA" : obj.toString();
				}
				list.add(row);
			}
			return list;
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();
		}
		return null;
	}

	public List<String[]> accountStatementBV(String mobNum) throws ClassNotFoundException {
		try {
			conn = createConnection("np_actor");

			String query = "SELECT DATE_FORMAT(created_date, '%d-%m-%Y') txn_date, DATE_FORMAT(created_date, '%H:%i') txn_time, "
					+ "SUBSTR(RIGHT(`comment`, 11),1,10) ref_no, SUBSTR(RIGHT(`comment`, 22),1,10) msisdn, "
					+ "SUBSTR(`comment`,1,LENGTH(`comment`)-24) description, "
					+ "CONCAT('-',SUBSTR(amount,1,LENGTH(`amount`)-4)) amount, 'NA' charge, 'NA' comm "
					+ "FROM wallet.`m_savings_account_transaction` "
					+ "WHERE `savings_account_id` = (SELECT id FROM wallet.`m_savings_account` "
					+ "WHERE account_no = (SELECT attr_value FROM master.organization_attribute "
					+ "WHERE attr_key = 'WALLET_ACCOUNT_NUMBER' AND orgnization_id = (SELECT organization FROM master.user "
					+ "WHERE id = (SELECT user_id FROM master.user_attribute WHERE attr_value = '" + mobNum + "' "
					+ "ORDER BY id DESC LIMIT 1)))) ORDER BY id DESC LIMIT 1";

			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			int nCol = rs.getMetaData().getColumnCount();
			List<String[]> list = new ArrayList<String[]>();
			while (rs.next()) {
				String[] row = new String[nCol];
				for (int iCol = 1; iCol <= nCol; iCol++) {
					Object obj = rs.getObject(iCol);
					row[iCol - 1] = (obj == null) ? "NA" : obj.toString();
				}
				list.add(row);
			}
			return list;
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();
		}
		return null;
	}

	public List<String[]> accountStatementCMS(String mobNum) throws ClassNotFoundException {
		try {
			conn = createConnection("np_actor");

			String query = "";

			if (txnDetailsFromIni("GetComm", "").equals("0.0")) {
				query = "SELECT DATE_FORMAT(created_date, '%d-%m-%Y') txn_date, DATE_FORMAT(created_date, '%H:%i') txn_time, "
						+ "'NA' ref_no, 'NA' msisdn, `comment` description, CONCAT('-',SUBSTR(amount,1,LENGTH(`amount`)-4)) "
						+ "amount, 'NA' charge, 'NA' comm FROM "
						+ "wallet.`m_savings_account_transaction` WHERE `savings_account_id` = (SELECT id FROM "
						+ "wallet.`m_savings_account` WHERE account_no = (SELECT attr_value FROM master.organization_attribute "
						+ "WHERE attr_key = 'WALLET_ACCOUNT_NUMBER' AND orgnization_id = (SELECT organization FROM master.user "
						+ "WHERE id = (SELECT user_id FROM master.user_attribute WHERE attr_value = '" + mobNum
						+ "' ORDER BY id " + "DESC LIMIT 1 OFFSET 1)))) ORDER BY id DESC LIMIT 1";
			} else if (txnDetailsFromIni("GetTds", "").equals("0.0")) {
				query = "SELECT DATE_FORMAT(created_date, '%d-%m-%Y') txn_date, DATE_FORMAT(created_date, '%H:%i') txn_time, "
						+ "'NA' ref_no, 'NA' msisdn, `comment` description, CONCAT('-',SUBSTR(amount,1,LENGTH(`amount`)-4)) "
						+ "amount, 'NA' charge, (SELECT SUBSTR(amount,1,LENGTH(`amount`)-4) amount FROM wallet.`m_savings_account_transaction` WHERE "
						+ "`savings_account_id` = (SELECT id FROM wallet.`m_savings_account` WHERE account_no = (SELECT attr_value "
						+ "FROM master.organization_attribute WHERE attr_key = 'WALLET_ACCOUNT_NUMBER' AND orgnization_id = (SELECT "
						+ "organization FROM master.user WHERE id = (SELECT user_id FROM master.user_attribute WHERE attr_value = "
						+ "'" + mobNum
						+ "' ORDER BY id DESC LIMIT 1)))) ORDER BY id DESC LIMIT 1) comm, '0.00' tds FROM "
						+ "wallet.`m_savings_account_transaction` WHERE `savings_account_id` = (SELECT id FROM "
						+ "wallet.`m_savings_account` WHERE account_no = (SELECT attr_value FROM master.organization_attribute "
						+ "WHERE attr_key = 'WALLET_ACCOUNT_NUMBER' AND orgnization_id = (SELECT organization FROM master.user "
						+ "WHERE id = (SELECT user_id FROM master.user_attribute WHERE attr_value = '" + mobNum
						+ "' ORDER BY id " + "DESC LIMIT 1)))) ORDER BY id DESC LIMIT 1 OFFSET 1";
			} else {
				query = "SELECT DATE_FORMAT(created_date, '%d-%m-%Y') txn_date, DATE_FORMAT(created_date, '%H:%i') txn_time, "
						+ "'NA' ref_no, 'NA' msisdn, `comment` description, CONCAT('-',SUBSTR(amount,1,LENGTH(`amount`)-4)) "
						+ "amount, '0.00' charge, (SELECT SUBSTR(amount,1,LENGTH(`amount`)-4) amount FROM wallet.`m_savings_account_transaction` WHERE "
						+ "`savings_account_id` = (SELECT id FROM wallet.`m_savings_account` WHERE account_no = (SELECT attr_value "
						+ "FROM master.organization_attribute WHERE attr_key = 'WALLET_ACCOUNT_NUMBER' AND orgnization_id = (SELECT "
						+ "organization FROM master.user WHERE id = (SELECT user_id FROM master.user_attribute WHERE attr_value = "
						+ "'" + mobNum
						+ "' ORDER BY id DESC LIMIT 1)))) ORDER BY id DESC LIMIT 1) comm, (SELECT CONCAT('-',SUBSTR(amount,1,LENGTH(`amount`)-4)) "
						+ "amount FROM wallet.`m_savings_account_transaction` WHERE `savings_account_id` = (SELECT id FROM "
						+ "wallet.`m_savings_account` WHERE account_no = (SELECT attr_value FROM master.organization_attribute "
						+ "WHERE attr_key = 'WALLET_ACCOUNT_NUMBER' AND orgnization_id = (SELECT organization FROM master.user "
						+ "WHERE id = (SELECT user_id FROM master.user_attribute WHERE attr_value = '" + mobNum
						+ "' ORDER BY id DESC LIMIT 1)))) ORDER BY id DESC LIMIT 1 OFFSET 1) tds FROM "
						+ "wallet.`m_savings_account_transaction` WHERE `savings_account_id` = (SELECT id FROM "
						+ "wallet.`m_savings_account` WHERE account_no = (SELECT attr_value FROM master.organization_attribute "
						+ "WHERE attr_key = 'WALLET_ACCOUNT_NUMBER' AND orgnization_id = (SELECT organization FROM master.user "
						+ "WHERE id = (SELECT user_id FROM master.user_attribute WHERE attr_value = '" + mobNum
						+ "' ORDER BY id DESC LIMIT 1)))) ORDER BY id DESC LIMIT 1 OFFSET 2";
			}

			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			System.out.println(query);
			int nCol = rs.getMetaData().getColumnCount();
			List<String[]> list = new ArrayList<String[]>();
			while (rs.next()) {
				String[] row = new String[nCol];
				for (int iCol = 1; iCol <= nCol; iCol++) {
					Object obj = rs.getObject(iCol);
					row[iCol - 1] = (obj == null) ? "NA" : obj.toString();
				}
				list.add(row);
			}
			return list;
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();
		}
		return null;
	}

	public List<String[]> accountStatementBP(String mobNum) throws ClassNotFoundException {
		try {
			conn = createConnection("np_actor");

			String query = "SELECT DATE_FORMAT(created_date, '%d-%m-%Y') txn_date, DATE_FORMAT(created_date, '%H:%i') "
					+ "txn_time, 'NA' ref_no, 'NA' msisdn, `comment`, CONCAT('-',SUBSTR(amount,1,LENGTH(`amount`)-4)) "
					+ "amount, (SELECT CONCAT('-',SUBSTR(amount,1,LENGTH(`amount`)-4)) charge FROM "
					+ "wallet.`m_savings_account_transaction` WHERE `savings_account_id` = (SELECT id FROM "
					+ "wallet.`m_savings_account` WHERE account_no = (SELECT attr_value FROM master.organization_attribute "
					+ "WHERE attr_key = 'WALLET_ACCOUNT_NUMBER' AND orgnization_id = (SELECT organization FROM master.user "
					+ "WHERE id = (SELECT user_id FROM master.user_attribute WHERE attr_value = '" + mobNum
					+ "' ORDER BY id DESC LIMIT 1)))) ORDER BY id DESC LIMIT 1) charge, 'NA' comm, 'NA' tds "
					+ "FROM wallet.`m_savings_account_transaction` WHERE `savings_account_id` = (SELECT id "
					+ "FROM wallet.`m_savings_account` WHERE account_no = (SELECT attr_value FROM "
					+ "master.organization_attribute WHERE attr_key = 'WALLET_ACCOUNT_NUMBER' AND "
					+ "orgnization_id = (SELECT organization FROM master.user WHERE id = (SELECT user_id "
					+ "FROM master.user_attribute WHERE attr_value = '8884999352' ORDER BY id DESC LIMIT 1)))) "
					+ "ORDER BY id DESC LIMIT 1 OFFSET 1";

			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			int nCol = rs.getMetaData().getColumnCount();
			List<String[]> list = new ArrayList<String[]>();
			while (rs.next()) {
				String[] row = new String[nCol];
				for (int iCol = 1; iCol <= nCol; iCol++) {
					Object obj = rs.getObject(iCol);
					row[iCol - 1] = (obj == null) ? "NA" : obj.toString();
				}
				list.add(row);
			}
			return list;
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();
		}
		return null;
	}

	public List<String[]> accountStatementAEPS(String mobNum, String type) throws ClassNotFoundException {
		try {
			String walletKey = "", sign = "";
			if (type.equalsIgnoreCase("Deposit")) {
				walletKey = "WALLET_ACCOUNT_NUMBER";
				sign = "-";
			} else {
				walletKey = "CASH_OUT_WALLET_ACCOUNT_NUMBER";
			}
			conn = createConnection("np_actor");
			String query = "SELECT DATE_FORMAT(created_date, '%d-%m-%Y') `date`, (SELECT DATE_FORMAT(created_date, '%H:%i') "
					+ "FROM wallet.`m_savings_account_transaction` WHERE `savings_account_id` = (SELECT id "
					+ "FROM wallet.`m_savings_account` WHERE account_no = (SELECT attr_value "
					+ "FROM master.organization_attribute WHERE attr_key = '" + walletKey + "' "
					+ "AND orgnization_id = (SELECT organization FROM master.user WHERE id = (SELECT user_id "
					+ "FROM master.user_attribute WHERE attr_value = '" + mobNum
					+ "' ORDER BY id DESC LIMIT 1)))) ORDER BY id DESC LIMIT 1) `time`, aeps.novopay_txn_ref, '"
					+ getAEPSMobNum("GetAEPSMobNum") + "', 'Credit Agent for " + type
					+ " transaction with identifier: XXXX XXXX " + getAadhaarFromIni("GetAadhaarNum").substring(8, 12)
					+ "' description, CONCAT('" + sign
					+ "',LEFT(txn_amount, LENGTH(txn_amount)-2), '.', RIGHT(txn_amount, 2)) amount, '"
					+ (txnDetailsFromIni("GetTds", "").equalsIgnoreCase(".00") ? "NA" : txnDetailsFromIni("GetTds", ""))
					+ "' tds, '"
					+ (txnDetailsFromIni("GetComm", "").equalsIgnoreCase("0.00") ? "NA"
							: txnDetailsFromIni("GetComm", ""))
					+ "' comm FROM `np_aepstxn`.`aeps_transactions` aeps ORDER BY id DESC LIMIT 1";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			int nCol = rs.getMetaData().getColumnCount();
			List<String[]> list = new ArrayList<String[]>();
			while (rs.next()) {
				String[] row = new String[nCol];
				for (int iCol = 1; iCol <= nCol; iCol++) {
					Object obj = rs.getObject(iCol);
					row[iCol - 1] = (obj == null) ? "NA" : obj.toString();
				}
				list.add(row);
			}
			return list;
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();
		}
		return null;
	}

	public String closingBalance(String mobNum, String type) throws ClassNotFoundException {
		try {
			conn = createConnection("np_actor");
			String query = "SELECT running_balance_derived FROM wallet.`m_savings_account_transaction` "
					+ "WHERE `savings_account_id` = (SELECT id FROM wallet.`m_savings_account` "
					+ "WHERE account_no = (SELECT attr_value FROM master.organization_attribute " + "WHERE attr_key = '"
					+ type + "' AND orgnization_id = (SELECT organization FROM master.user "
					+ "WHERE id = (SELECT user_id FROM master.user_attribute WHERE attr_value = '" + mobNum
					+ "' ORDER BY id DESC LIMIT 1)))) ORDER BY id DESC LIMIT 1";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();
		}
		return null;
	}

	public List<String[]> aepsStatusEnquiry(String txnRefNo) throws ClassNotFoundException {
		try {
			conn = createConnection("np_actor");
			String query = "SELECT DATE_FORMAT(updated_date, '%b %e, %Y %l:%i:%s %p') txn_date, novopay_txn_ref, "
					+ "txn_type, (SELECT `value` FROM np_actor.platform_master_data WHERE `code` = "
					+ "(SELECT bank_code FROM np_aepstxn.aeps_transactions ORDER BY id DESC LIMIT 1) "
					+ "AND data_sub_type = (SELECT txn_type FROM np_aepstxn.aeps_transactions WHERE novopay_txn_ref = '"
					+ txnRefNo + "') AND partner_code = 'RBL') bank, CONCAT(LEFT(txn_amount, LENGTH(txn_amount)-2),'.',"
					+ "RIGHT(txn_amount, 2)) amount, "
					+ "IF(`status`='FAIL' AND refund_status='COMPLETED','REFUNDED',IF(`status`='SUSPECT','SUCCESS',"
					+ "IF(refund_status = 'ELIGIBLE','Ready For Refund',`status`))) `status` "
					+ "FROM np_aepstxn.aeps_transactions WHERE novopay_txn_ref = '" + txnRefNo + "'";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			int nCol = rs.getMetaData().getColumnCount();
			List<String[]> list = new ArrayList<String[]>();
			while (rs.next()) {
				String[] row = new String[nCol];
				for (int iCol = 1; iCol <= nCol; iCol++) {
					Object obj = rs.getObject(iCol);
					row[iCol - 1] = (obj == null) ? "" : obj.toString();
				}
				list.add(row);
			}
			return list;
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();
		}
		return null;
	}

	public List<String[]> mtStatusEnquiry(String txnRefNo) throws ClassNotFoundException {
		try {
			conn = createConnection("np_actor");
			if (txnRefNo == "-") {
				txnRefNo = paymentRefCode("all");
			}
			String query = "SELECT CONCAT(DATE_FORMAT(created_on, '%e %b %Y, '), UPPER(DATE_FORMAT(created_on, '%h:%i:%s %p'))) `date`, 'Remittance' `type`, "
					+ "payment_ref_code, CONCAT(LEFT(amount, LENGTH(amount)-2), '.', RIGHT(amount, 2)) amount, beneficiary_name, beneficiary_bank, "
					+ "beneficiary_ifsc_code, beneficiary_acc_num, CONCAT(DATE_FORMAT(last_updated_on, '%e %b %Y, '), UPPER(DATE_FORMAT(last_updated_on, '%h:%i:%s %p'))) "
					+ "FROM `np_remittance`.`remittance_outward_table` WHERE payment_ref_code = '" + txnRefNo + "'";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			int nCol = rs.getMetaData().getColumnCount();
			List<String[]> list = new ArrayList<String[]>();
			while (rs.next()) {
				String[] row = new String[nCol];
				for (int iCol = 1; iCol <= nCol; iCol++) {
					Object obj = rs.getObject(iCol);
					row[iCol - 1] = (obj == null) ? "NA" : obj.toString();
				}
				list.add(row);
			}
			return list;
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();
		}
		return null;
	}

	public String getMobNumFromOrgCode() throws ClassNotFoundException {
		try {
			conn = createConnection("np_actor");
			String query = "SELECT attr_value FROM master.user_attribute WHERE user_id = (SELECT id "
					+ "FROM master.user WHERE organization = (SELECT id FROM master.organization "
					+ "WHERE `code` = (SELECT agent_org_code FROM `np_aepstxn`.`aeps_transactions` "
					+ "ORDER BY id DESC LIMIT 1))) AND attr_key = 'MSISDN'";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();
		}
		return null;
	}

	public String aepsStatusEnquiryDate(String txnRefNo) throws ClassNotFoundException {
		try {
			conn = createConnection("np_actor");
			String query = "SELECT DATE_FORMAT(updated_date, '%d/%m') txn_date FROM np_aepstxn.aeps_transactions "
					+ "WHERE novopay_txn_ref = '" + txnRefNo + "'";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();
		}
		return null;
	}

	public String deleteAEPSTxn() throws ClassNotFoundException {
		try {
			conn = createConnection("rblsimulator");
			String query = "DELETE FROM `limit_charges`.`entity_consumed_limits` WHERE "
					+ "entity_id = '0fa44628d16028a273781693d392256e502e66ccd6b6a102def50fd729457855bcb8a44fcde70d"
					+ "dd3d4afeeff82d1e9a6348d2de7fe5ed5b9eee3a386cdcf2e0';";
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();

		}
		return null;
	}

	public String aepsTxnDate() throws ClassNotFoundException {
		try {
			conn = createConnection("np_actor");
			String query = "SELECT DATE_FORMAT(updated_date, '%d-%b-%Y %h:%i %p') FROM `np_aepstxn`.`aeps_transactions` "
					+ "ORDER BY id DESC LIMIT 1";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();
		}
		return null;
	}

	public String aepsRefNum() throws ClassNotFoundException {
		try {
			conn = createConnection("np_actor");
			String query = "SELECT novopay_txn_ref FROM `np_aepstxn`.`aeps_transactions` ORDER BY id DESC LIMIT 1";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();
		}
		return null;
	}

	public String updateAEPSTxn(String txnRef) throws ClassNotFoundException {
		try {
			conn = createConnection("master");
			String query = "UPDATE np_aepstxn.aeps_transactions SET `status` = 'FAIL', partner_txn_status = 'FAIL',"
					+ " partner_txn_status_code = 'M3', refund_status = 'ELIGIBLE' WHERE novopay_txn_ref = '" + txnRef
					+ "';";
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB!! Update failed..!");
			sqe.printStackTrace();

		}
		return null;
	}

	public List<String[]> saturdayDate(String firstDate) throws ClassNotFoundException {
		try {
			conn = createConnection("np_actor");
			String query = "SELECT DATE_ADD((" + firstDate + " + INTERVAL -(SELECT DATE_FORMAT(" + firstDate
					+ ", '%d') - 1) DAY), INTERVAL(( 7 - DAYOFWEEK(" + firstDate + " + INTERVAL -(SELECT DATE_FORMAT("
					+ firstDate + ", '%d') - 1) DAY) ) % 7 ) + 7 DAY) AS saturday_of_month\r\n" + "UNION\r\n"
					+ "SELECT DATE_ADD((" + firstDate + " + INTERVAL -(SELECT DATE_FORMAT(" + firstDate
					+ ", '%d') - 1) DAY), INTERVAL(( 7 - DAYOFWEEK(" + firstDate + " + INTERVAL -(SELECT DATE_FORMAT("
					+ firstDate + ", '%d') - 1) DAY) ) % 7 ) + 21 DAY) AS saturday_of_month";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			int nCol = rs.getMetaData().getColumnCount();
			List<String[]> list = new ArrayList<String[]>();
			while (rs.next()) {
				String[] row = new String[nCol];
				for (int iCol = 1; iCol <= nCol; iCol++) {
					Object obj = rs.getObject(iCol);
					row[iCol - 1] = (obj == null) ? "NA" : obj.toString();
				}
				list.add(row);
			}
			return list;
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();
		}
		return null;
	}

	public String doTransferDate() throws ClassNotFoundException {
		try {
			conn = createConnection("np_actor");
			String query = "SELECT DATE_FORMAT(created_on, '%d-%m-%Y %H:%i') FROM service_repo.service_data_audit "
					+ "WHERE api_name = 'doTransfer' ORDER BY id DESC LIMIT 1";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();
		}
		return null;
	}

	public String doTransferDateWithIncreasedTime() throws ClassNotFoundException {
		try {
			conn = createConnection("np_actor");
			String query = "SELECT IF((SELECT DATE_FORMAT(created_on, '%i') FROM service_repo.service_data_audit "
					+ "WHERE api_name = 'doTransfer' ORDER BY id DESC LIMIT 1)<10,(CONCAT((SELECT DATE_FORMAT(created_on, "
					+ "'%d-%m-%Y %H:') FROM service_repo.service_data_audit WHERE api_name = 'doTransfer' ORDER BY id DESC "
					+ "LIMIT 1),\"0\",(SELECT DATE_FORMAT(created_on, '%i')+1 FROM service_repo.service_data_audit WHERE "
					+ "api_name = 'doTransfer' ORDER BY id DESC LIMIT 1))),(CONCAT((SELECT DATE_FORMAT(created_on, "
					+ "'%d-%m-%Y %H:') FROM service_repo.service_data_audit WHERE api_name = 'doTransfer' ORDER BY id "
					+ "DESC LIMIT 1),(SELECT DATE_FORMAT(created_on, '%i')+1 FROM service_repo.service_data_audit WHERE "
					+ "api_name = 'doTransfer' ORDER BY id DESC LIMIT 1))))";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();
		}
		return null;
	}

	public void updateOrgSettlementInfo(String mode, String status, String enabled, String remarks, String mobNum)
			throws ClassNotFoundException {
		try {
			conn = createConnection("master");
			String query = "UPDATE master.`org_stlmnt_info` SET settlement_mode = '" + mode + "', `status` = '" + status
					+ "', `enabled` = '" + enabled + "', blocking_remarks = '" + remarks
					+ "' WHERE `organization_id` = (SELECT organization FROM master.user WHERE id IN (SELECT user_id "
					+ "FROM master.user_attribute WHERE attr_key = 'MSISDN' AND attr_value = '" + mobNum
					+ "') AND `status` = 'ACTIVE') ORDER BY id DESC LIMIT 1;";
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
			System.out.println("Updating org_stlmnt_info table as per mode " + mode + " and status " + status);
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB!! Update failed..!");
			sqe.printStackTrace();
		}
	}

	public String deleteOrgSettlementInfo(String mobNum) throws ClassNotFoundException {
		try {
			conn = createConnection("rblsimulator");
			String query = "DELETE FROM `master`.`org_stlmnt_info` WHERE `organization_id` = (SELECT "
					+ "organization FROM master.user WHERE id IN (SELECT user_id FROM master.user_attribute "
					+ "WHERE attr_key = 'MSISDN' AND attr_value = '" + mobNum + "' AND `status` = 'ACTIVE'));";
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();

		}
		return null;
	}

	public void insertOrgSettlementInfo(String ifsc, String mode, String status, String enabled, String mobNum,
			String primary, String accNum, String date) throws ClassNotFoundException {
		try {
			conn = createConnection("master");
			stmt = conn.createStatement();
			String insertQuery = "INSERT INTO `master`.`org_stlmnt_info` (`organization_id`, `ac_no`, `bank_name`, `ifsc_code`,"
					+ " `ac_holder_name`, `settlement_mode`, `is_primary`, `mdr_code`, `financial_category`, `frequency`,"
					+ " `info_proof`, `info_proof_urn`, `info_proof_version`, `start_date`, `end_date`, `update_type`,"
					+ " `update_remark`, `updated_on`, `created_by`, `inspected_by`, `inspected_on`, `status`,"
					+ " `inspection_comments`, `validation_remarks`, `enabled`, `blocking_remarks`) VALUES((SELECT "
					+ "organization FROM master.user WHERE id IN (SELECT user_id FROM master.user_attribute WHERE "
					+ "attr_key = 'MSISDN' AND attr_value = '" + mobNum + "' AND `status` = 'ACTIVE')),'" + accNum + "'"
					+ ",'HDFC BANK','" + ifsc + "','Ankush Khanna','" + mode + "','" + primary
					+ "','MRCHNT_MDR_DEFAULT',"
					+ "'MICRO','DAILY',NULL,'dce3aa24-479c-4069-a8df-c14197896531',NULL,NOW(),NULL,'ACCOUNT_DETAILS',"
					+ "'1234567890, HDFC BANK',NOW(),'f4124888-6d00-4005-a6cf-778dea9f89d7','ankush_1608216767690',"
					+ date + ",'" + status + "',NULL,NULL,'" + enabled + "',NULL);";
			stmt.executeUpdate(insertQuery);
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB!! Update failed..!");
			sqe.printStackTrace();
		}
	}

	public String cfcDate() throws ClassNotFoundException {
		try {
			conn = createConnection("np_actor");
			String query = "SELECT SUBSTR(created_on,1,16) FROM `np_actor`.`tx_audit` ORDER BY id DESC LIMIT 1";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();
		}
		return null;
	}

	public String cfcRefNum() throws ClassNotFoundException {
		try {
			conn = createConnection("np_actor");
			String query = "SELECT novopay_ref_code FROM `np_actor`.`tx_audit` ORDER BY id DESC LIMIT 1";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();
		}
		return null;
	}

	public void modifyContract(String contrct, String mobNum) throws ClassNotFoundException {
		String contract = contrct;
		try {
			conn = createConnection("master");
			String deleteQuery = "DELETE FROM master.contract WHERE organization = (SELECT u.`organization` FROM `master`.`user` u "
					+ "JOIN `master`.`user_attribute` ua ON u.`id`=ua.`user_id` WHERE ua.`attr_value`='" + mobNum + "' "
					+ "AND u.status = 'ACTIVE')";

			String insertQuery1 = "INSERT INTO `contract` (`organization`, `partner_organization`) "
					+ "VALUES((SELECT u.`organization` FROM `master`.`user` u JOIN `master`.`user_attribute` ua "
					+ "ON u.`id`=ua.`user_id` WHERE ua.`attr_value`='" + mobNum + "' "
					+ "AND u.status = 'ACTIVE'),(SELECT id " + "FROM master.organization WHERE `CODE` = '" + contract
					+ "'));";
			String insertQuery2 = "INSERT INTO `contract` (`organization`, `partner_organization`) "
					+ "VALUES((SELECT u.`organization` FROM `master`.`user` u JOIN `master`.`user_attribute` ua "
					+ "ON u.`id`=ua.`user_id` WHERE ua.`attr_value`='" + mobNum + "' "
					+ "AND u.status = 'ACTIVE'),(SELECT id " + "FROM master.organization WHERE `CODE` = 'rbl'));";

			stmt = conn.createStatement();
			stmt.executeUpdate(deleteQuery);
			System.out.println("Deleting all contracts");
			stmt.executeUpdate(insertQuery1);
			//stmt.executeUpdate(insertQuery2);

			System.out.println("Inserting contract: rbl and " + contract.toLowerCase());
			//System.out.println("Inserting contract:" + contract.toLowerCase());
			
			//29/06/25
			RedisRemoteFlush.flushRedisUsingJSch();
			System.out.println("Flushall done");
			
			
		} catch (SQLException sqe) {
			System.out.println("Duplicate entry for contract: " + contract);
		}
	}

	public void insertContract(String mobNum) throws ClassNotFoundException {
		try {
			conn = createConnection("master");
			stmt = conn.createStatement();

			List<String> org_code = new ArrayList<String>();
			org_code.add("novopay");
			org_code.add("rbl");
			org_code.add("ybl");
			org_code.add("nsdl");
			org_code.add("fingpay");
			org_code.add("paytm");
			org_code.add("fino");
			org_code.add("cms");
			org_code.add("billpay");
			org_code.add("recharges");
			org_code.add("wallet");
			org_code.add("pg-wallet");
			org_code.add("gold");
			org_code.add("INS_CLINIK");
			org_code.add("INS_ADITYA_BIRLA");
			org_code.add("INS_ZOPPER");
			org_code.add("equitas");
			org_code.add("indusind");
			org_code.add("np_loans");
			org_code.add("np_chatbot");
			org_code.add("banking_novopay");
			org_code.add("YBL_DEPOSIT");
			org_code.add("FINGPAY_DEPOSIT");
			org_code.add("YBL_AADHAAR_PAY");
			org_code.add("FINGPAY_AADHAAR_PAY");


			for (String code : org_code) {
				String insertQuery = "INSERT INTO `contract` (`organization`, `partner_organization`) "
						+ "VALUES((SELECT u.`organization` FROM `master`.`user` u JOIN `master`.`user_attribute` ua "
						+ "ON u.`id`=ua.`user_id` WHERE ua.`attr_value`='" + mobNum + "' AND u.status "
						+ "= 'ACTIVE'),(SELECT id FROM master.organization WHERE `CODE` = '" + code + "'));";
				try {
					stmt.executeUpdate(insertQuery);
				} catch (Exception e) {
					System.out.println("Duplicate entry for " + code);
				}
			}
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB!! Update failed..!");
			sqe.printStackTrace();
		}
	}

	public void updateSettlementModeInPlatformMasterData(String value, String partner) throws ClassNotFoundException {
		try {
			conn = createConnection("master");
			String query = "UPDATE np_actor.`platform_master_data` SET `value` = '" + value
					+ "', last_updated_on = NOW() WHERE partner_code = '" + partner
					+ "' AND data_sub_type = 'CASHOUT_ON_DEMAND_SETTLEMENT' AND " + "`code` = 'IMPS_DISABLE';";
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
			System.out.println("Updating IMPS_DISABLE as " + value + " for partner " + partner);
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB!! Update failed..!");
			sqe.printStackTrace();
		}
	}

	public String settlementServiceUnavailableMessage() throws ClassNotFoundException {
		try {
			conn = createConnection("limit_charges");
			String query = "SELECT `desc` FROM `np_errors`.`novopay_codes` WHERE `code` = '220700';";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();

		}
		return null;
	}

	public void updatePublicHoliday(String partner, String date) throws ClassNotFoundException {
		try {
			conn = createConnection("master");
			String query1 = "TRUNCATE TABLE `np_master`.`public_holidays`;";
			String query2 = "INSERT INTO `np_master`.`public_holidays` (partner, `date`) VALUES ('"
					+ partner.toLowerCase() + "'," + date + ")";
			stmt = conn.createStatement();
			stmt.executeUpdate(query1);
			stmt.executeUpdate(query2);
			System.out.println("Updating public holiday as " + date);
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB!! Update failed..!");
			sqe.printStackTrace();
		}
	}

	public void updateAepsPartner(String partner, String mobNum) throws ClassNotFoundException {
		try {
			conn = createConnection("master");
			String query = "UPDATE master.organization_attribute SET attr_value = '" + partner
					+ "' WHERE orgnization_id = (SELECT organization FROM master.user "
					+ "WHERE id IN (SELECT user_id FROM master.user_attribute WHERE attr_value = '" + mobNum
					+ "') AND `status` = 'ACTIVE') AND attr_key = 'AEPS_PARTNER';";
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
			System.out.println("Updating AEPS Partner as " + partner);
			RedisRemoteFlush.flushRedisUsingJSch();
			System.out.println("Flushall done");
		} catch (SQLException sqe) {
			System.out.println("Error executing query");
			sqe.printStackTrace();
		}
	}

	public void updateDepositerPartner(String partner, String mobNum) throws ClassNotFoundException {
		try {
			conn = createConnection("master");
			String query = "UPDATE master.organization_attribute SET attr_value = '" + partner
					+ "' WHERE orgnization_id = (SELECT organization FROM master.user "
					+ "WHERE id IN (SELECT user_id FROM master.user_attribute WHERE attr_value = '" + mobNum
					+ "') AND `status` = 'ACTIVE') AND attr_key = 'DEPOSIT_PARTNER';";
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
			System.out.println("Updating DEPOSIT Partner as " + partner);
			RedisRemoteFlush.flushRedisUsingJSch();
			System.out.println("Flushall done");
		} catch (SQLException sqe) {
			System.out.println("Error executing query");
			sqe.printStackTrace();
		}
	}


public void updateAadhaarpayPartner(String partner, String mobNum) throws ClassNotFoundException {
		try {
			conn = createConnection("master");
			String query = "UPDATE master.organization_attribute SET attr_value = '" + partner
					+ "' WHERE orgnization_id = (SELECT organization FROM master.user "
					+ "WHERE id IN (SELECT user_id FROM master.user_attribute WHERE attr_value = '" + mobNum
					+ "') AND `status` = 'ACTIVE') AND attr_key = 'AADHAAR_PAY_PARTNER';";
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
			System.out.println("Updating AADHAARPAY Partner as " + partner);
			RedisRemoteFlush.flushRedisUsingJSch();
			System.out.println("Flushall done");
		} catch (SQLException sqe) {
			System.out.println("Error executing query");
			sqe.printStackTrace();
		}
	}
	public void updateSettlementPartner(String setpartner, String aepspartner, String mode)
			throws ClassNotFoundException {
		try {
			conn = createConnection("master");
			String query = "UPDATE np_actor.`platform_master_data` SET `value` = '" + setpartner
					+ "', last_updated_on = NOW() WHERE `code` = '" + aepspartner + "' AND data_sub_type = '" + mode
					+ "_SETTLEMENT_PARTNER' AND data_type = 'ONDEMAND_SETTLEMENT';";
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
			System.out.println("Updating Settlement Partner as " + setpartner);
		} catch (SQLException sqe) {
			System.out.println("Error executing query");
			sqe.printStackTrace();
		}
	}

	public void updateSetllementStartAndEndTime(String partner, String mode, String startTime, String endTime)
			throws ClassNotFoundException {
		try {
			conn = createConnection("master");
			String query1 = "UPDATE np_actor.`platform_master_data` SET `value` = " + startTime
					+ ", last_updated_on = NOW() WHERE data_type = 'SETTLEMENT' AND data_sub_type = '" + partner
					+ "' AND `code` = '" + mode + "_START_TIME';";
			String query2 = "UPDATE np_actor.`platform_master_data` SET `value` = " + endTime
					+ ", last_updated_on = NOW() WHERE data_type = 'SETTLEMENT' AND data_sub_type = '" + partner
					+ "' AND `code` = '" + mode + "_END_TIME';";
			stmt = conn.createStatement();
			stmt.executeUpdate(query1);
			stmt.executeUpdate(query2);
			System.out.println("Updating start time as " + startTime + " and end time as " + endTime);
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB!! Update failed..!");
			sqe.printStackTrace();
		}
	}

	public String requestIdFromTopUpRequest(String email) throws ClassNotFoundException {
		try {
			conn = createConnection("limit_charges");
			String query = "SELECT request_id FROM `np_ops`.`wallet_topup_request` WHERE email = '" + email + "';";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();

		}
		return null;
	}

	public String createdDateTimeFromTopUpRequest(String email) throws ClassNotFoundException {
		try {
			conn = createConnection("limit_charges");
			String query = "SELECT DATE_FORMAT(created_on, '%H:%i %d/%m/%y') FROM `np_ops`.`wallet_topup_request` WHERE email = '"
					+ email + "';";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();

		}
		return null;
	}

	public void updateDmtPartner(String partner, String mobNum) throws ClassNotFoundException {
		try {
			conn = createConnection("master");
			String query = "UPDATE master.organization_attribute SET attr_value = '" + partner
					+ "' WHERE orgnization_id = (SELECT organization FROM master.user "
					+ "WHERE id IN (SELECT user_id FROM master.user_attribute WHERE attr_value = '" + mobNum
					+ "') AND `status` = 'ACTIVE') AND attr_key = 'DMT_PARTNER';";
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
			System.out.println("Updating DMT Partner as " + partner);
			RedisRemoteFlush.flushRedisUsingJSch();
			System.out.println("Flushall done");
		} catch (SQLException sqe) {
			System.out.println("Error executing query");
			sqe.printStackTrace();
		}
	}

	public void updateDmtBcAgentId(String value, String mobNum) throws ClassNotFoundException {
		try {
			conn = createConnection("master");
			String query = "UPDATE master.organization_attribute SET attr_value = '" + value
					+ "' WHERE orgnization_id = (SELECT organization FROM master.user "
					+ "WHERE id IN (SELECT user_id FROM master.user_attribute WHERE attr_value = '" + mobNum
					+ "') AND `status` = 'ACTIVE') AND attr_key = 'RBL_BCAGENT_ID';";
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
			System.out.println("Updating DMT BC Agent ID as " + value);
		} catch (SQLException sqe) {
			System.out.println("Error executing query");
			sqe.printStackTrace();
		}
	}
	
	

	public void updateWalletTopupRequest() throws ClassNotFoundException {
		try {
			conn = createConnection("np_ops");
			String query = "UPDATE np_ops.`wallet_topup_request` SET `status` = 'APPROVED', "
					+ "`processed_on` = NOW() WHERE `status` = 'PENDING';";
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
			System.out.println("Updating status of wallet_topup_request to APPROVED");
		} catch (SQLException sqe) {
			System.out.println("Error executing query");
			sqe.printStackTrace();
		}
	}

	public void updateCdmWalletLoad() throws ClassNotFoundException {
		try {
			conn = createConnection("np_ops");
			String query = "UPDATE np_ops.`cdm_wallet_load` SET `status` = 'COMPLETED' WHERE `status` = 'PENDING'";
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
			System.out.println("Updating status of cdm_wallet_load to APPROVED");
		} catch (SQLException sqe) {
			System.out.println("Error executing query");
			sqe.printStackTrace();
		}
	}

	public void insertCdmWalletLoadEntries(String partner, String term, String txn, String amount, String type,
			String desc) throws ClassNotFoundException {
		try {
			conn = createConnection("np_ops");
			stmt = conn.createStatement();

			String insertQuery = "INSERT INTO np_ops.`cdm_wallet_load` (`partner`, `machine_id`, `txn_id`, `txn_date`, "
					+ "`amount`, `txn_description`, `bal_after_txn`, `status`, `created_by`, `created_on`, "
					+ "`last_updated_by`, `last_updated_on`, `file_name`, `remarks`, `wallet_request_id`, "
					+ "`cash_deposit_type`, `additional_data`) VALUES('" + partner + "','" + term + "','" + txn
					+ "',NOW(),'" + amount + "','" + desc + "','1234000',"
					+ "'PENDING','Automation',NOW(),NULL,NULL,'Automation.csv',NULL,NULL,'" + type + "','BENGALURU');";
			stmt.executeUpdate(insertQuery);
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB!! Update failed..!");
			sqe.printStackTrace();
		}
	}

	public void updatePartnerStatus(String partner1, String p1status, String partner2, String p2status, String partner3,
			String p3status) throws ClassNotFoundException {
		try {
			conn = createConnection("np_master");
			String query1 = "UPDATE `np_master`.`partner_status` SET `status` = '" + p1status + "' WHERE partner = '"
					+ partner1 + "';";
			String query2 = "UPDATE `np_master`.`partner_status` SET `status` = '" + p2status + "' WHERE partner = '"
					+ partner2 + "';";
			String query3 = "UPDATE `np_master`.`partner_status` SET `status` = '" + p3status + "' WHERE partner = '"
					+ partner3 + "';";
			stmt = conn.createStatement();
			stmt.executeUpdate(query1);
			stmt.executeUpdate(query2);
			stmt.executeUpdate(query3);
			System.out.println("Updating partner status: " + partner1 + "=" + p1status + " , " + partner2 + "="
					+ p2status + " , " + partner3 + "=" + p3status);
		} catch (SQLException sqe) {
			System.out.println("Error executing query");
			sqe.printStackTrace();
		}
	}

	public String getOrderId(String orderId) throws ClassNotFoundException {
		try {
			conn = createConnection("np_payment_gateway");
			String query = "SELECT `status` FROM `np_payment_gateway`.`order_details` WHERE order_id = '" + orderId
					+ "';";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();

		}
		return null;
	}

	public String getAccountValidationCharge() throws ClassNotFoundException {
		try {
			conn = createConnection("np_payment_gateway");
			String query = "SELECT `value` FROM np_actor.`platform_master_data` WHERE `code` = 'ACCOUNT_VALIDATION_CHARGE'";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();

		}
		return null;
	}

	public String getRBLEKYCStatus(String mobNum) throws ClassNotFoundException {
		try {
			conn = createConnection("np_payment_gateway");
			String query = "SELECT attr_value FROM master.organization_attribute WHERE "
					+ "orgnization_id = (SELECT organization FROM master.user "
					+ "WHERE id IN (SELECT user_id FROM master.user_attribute WHERE attr_value = '" + mobNum
					+ "') AND `status` = 'ACTIVE') AND attr_key = 'RBL_EKYC_STATUS';";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();

		}
		return null;
	}

	public void updateRBLEKYCStatus(String value, String mobNum) throws ClassNotFoundException {
		try {
			conn = createConnection("master");
			String query = "UPDATE master.organization_attribute SET attr_value = '" + value
					+ "' WHERE orgnization_id = (SELECT organization FROM master.user "
					+ "WHERE id IN (SELECT user_id FROM master.user_attribute WHERE attr_value = '" + mobNum
					+ "') AND `status` = 'ACTIVE') AND attr_key = 'RBL_EKYC_STATUS';";
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
			System.out.println("Updating RBL EKYC Status as " + value);
		} catch (SQLException sqe) {
			System.out.println("Error executing query");
			sqe.printStackTrace();
		}
	}

	public void updateFingpayTwoFAStatus(String string, String mobNum) throws ClassNotFoundException {
		try {
			conn = createConnection("master");
			String query = "UPDATE master.organization_attribute SET attr_value = '" + string
					+ "' WHERE orgnization_id = (SELECT organization FROM master.user "
					+ "WHERE id IN (SELECT user_id FROM master.user_attribute WHERE attr_value = '" + mobNum
					+ "') AND `status` = 'ACTIVE') AND attr_key = 'FINGPAY_AEPS_LAST_LOGIN_DATE';";
			
			
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
			System.out.println("Updating FINGPAY_AEPS_LAST_LOGIN_DATE Status as " + string);
			RedisRemoteFlush.flushRedisUsingJSch();
			System.out.println("Flushall done");
		} catch (SQLException sqe) {
			System.out.println("Error executing query");
			sqe.printStackTrace();
		}
	}
	
	public void updateAadharpayFingpayTwoFAStatus(String string, String mobNum) throws ClassNotFoundException {
		try {
			conn = createConnection("master");
			String query = "UPDATE master.organization_attribute SET attr_value = '" + string
					+ "' WHERE orgnization_id = (SELECT organization FROM master.user "
					+ "WHERE id IN (SELECT user_id FROM master.user_attribute WHERE attr_value = '" + mobNum
					+ "') AND `status` = 'ACTIVE') AND attr_key = 'FINGPAY_APAY_LAST_LOGIN_DATE';";
			
			
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
			System.out.println("Updating FINGPAY_APAY_LAST_LOGIN_DATE Status as " + string);
			RedisRemoteFlush.flushRedisUsingJSch();
			System.out.println("Flushall done");
		} catch (SQLException sqe) {
			System.out.println("Error executing query");
			sqe.printStackTrace();
		}
	}
	
	
	
	public void updateYblTwoFAStatus(String string, String mobNum) throws ClassNotFoundException {
		try {
			conn = createConnection("master");
			String query = "UPDATE master.organization_attribute SET attr_value = '" + string
					+ "' WHERE orgnization_id = (SELECT organization FROM master.user "
					+ "WHERE id IN (SELECT user_id FROM master.user_attribute WHERE attr_value = '" + mobNum
					+ "') AND `status` = 'ACTIVE') AND attr_key = 'YBL_AEPS_LAST_LOGIN_DATE';";
			
			
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
			System.out.println("Updating YBL_AEPS_LAST_LOGIN_DATE Status as " + string);
			RedisRemoteFlush.flushRedisUsingJSch();
			System.out.println("Flushall done");
		} catch (SQLException sqe) {
			System.out.println("Error executing query");
			sqe.printStackTrace();
		}
	}

	public void updatensdlTwoFAStatus(String string, String mobNum) throws ClassNotFoundException {
		try {
			conn = createConnection("master");
			String query = "UPDATE master.organization_attribute SET attr_value = '" + string
					+ "' WHERE orgnization_id = (SELECT organization FROM master.user "
					+ "WHERE id IN (SELECT user_id FROM master.user_attribute WHERE attr_value = '" + mobNum
					+ "') AND `status` = 'ACTIVE') AND attr_key = 'YBL_AEPS_LAST_LOGIN_DATE';";
			
			
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
			System.out.println("Updating NSDL_AEPS_LAST_LOGIN_DATE Status as " + string);
			RedisRemoteFlush.flushRedisUsingJSch();
			System.out.println("Flushall done");
		} catch (SQLException sqe) {
			System.out.println("Error executing query");
			sqe.printStackTrace();
		}
	}
	
	public void updateRBLTwoFAStatus(String string, String mobNum) throws ClassNotFoundException {
		try {
			conn = createConnection("master");
			String query = "UPDATE master.organization_attribute SET attr_value = '" + string
					+ "' WHERE orgnization_id = (SELECT organization FROM master.user "
					+ "WHERE id IN (SELECT user_id FROM master.user_attribute WHERE attr_value = '" + mobNum
					+ "') AND `status` = 'ACTIVE') AND attr_key = 'AEPS_LAST_LOGIN_DATE';";
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
			System.out.println("Updating AEPS_LAST_LOGIN_DATE Status as " + string);
		} catch (SQLException sqe) {
			System.out.println("Error executing query");
			sqe.printStackTrace();
		}
	}
	public String getOrgAttributeValue(String mobNum, String key) throws ClassNotFoundException {
		try {
			conn = createConnection("np_payment_gateway");
			String query = "SELECT attr_value FROM master.organization_attribute WHERE attr_key = '" + key + "'"
					+ " AND orgnization_id = (SELECT organization FROM master.user WHERE id IN (SELECT user_id FROM "
					+ "master.user_attribute WHERE attr_value = '" + mobNum + "') AND `status` = 'ACTIVE');";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();

		}
		return null;
	}

	public void insertIntoOrgAttribute(String mobNum, String key, String value) throws ClassNotFoundException {
		try {
			conn = createConnection("master");
			String query = "INSERT INTO master.organization_attribute (`attr_key`, `attr_value`, `orgnization_id`) "
					+ "VALUES('" + key + "','" + value + "',(SELECT organization FROM master.user "
					+ "WHERE id IN (SELECT user_id FROM master.user_attribute WHERE attr_value = '" + mobNum
					+ "') AND `status` = 'ACTIVE'));";
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
			System.out.println("Inserting attribute key as " + key + " and value as " + value);
		} catch (SQLException sqe) {
			System.out.println("Error executing query");
			sqe.printStackTrace();
		}
	}

	public void updateSettlementStatus(String status, String txnId) throws ClassNotFoundException {
		try {
			conn = createConnection("master");
			String query = "UPDATE `simulator_settlement`.`settlement_simulator_txn` SET `status` = '" + status
					+ "' WHERE txn_id = '" + txnId + "'";
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
			System.out.println("Updating Settlement Status as " + status);
		} catch (SQLException sqe) {
			System.out.println("Error executing query");
			sqe.printStackTrace();
		}
	}

	public String getMpin(String mobNum) throws ClassNotFoundException {
		try {
			conn = createConnection("np_payment_gateway");
			String query = "SELECT uam.value mpin FROM `master`.`user` u JOIN `master`.`user_attribute` ua "
					+ "ON u.`id`=ua.`user_id` JOIN `master`.user_auth_mechanism uam ON u.`id` = uam.`user_id` "
					+ "WHERE ua.`attr_value` = '" + mobNum + "' AND u.`status` = 'ACTIVE';";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();

		}
		return null;
	}

	public void deleteMpinHistory(String mobNum) throws ClassNotFoundException {
		try {
			conn = createConnection3("npretail_actor");
			String query = "DELETE FROM `npretail_actor`.`user_auth_value_history` WHERE user_id = (SELECT user_id "
					+ "FROM `npretail_actor`.`user_handle` WHERE `value` = '" + mobNum + "' AND is_deleted = '0')";
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
			System.out.println("Deleted MPIN history for " + mobNum);
		} catch (SQLException sqe) {
			System.out.println("Error executing query");
			sqe.printStackTrace();
		}
	}

	public void insertMpin(String mobNum) throws ClassNotFoundException {
		try {
			conn = createConnection3("npretail_actor");
			String query = "INSERT INTO `user_auth_value_history` (`user_id`, `auth_value`, `user_auth_type_id`) "
					+ "VALUES((SELECT user_id FROM npretail_actor.user_handle WHERE VALUE = '" + mobNum
					+ "' AND is_deleted = '0'),'0ffe1abd1a08215353c233d6e009613e95eec4253832a761af28ff37ac5a150c',"
					+ "(SELECT id FROM `npretail_actor`.`user_auth_type` WHERE `type` = 'MPIN'));";
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
			System.out.println("Deleted MPIN history for " + mobNum);
		} catch (SQLException sqe) {
			System.out.println("Error executing query");
			sqe.printStackTrace();
		}
	}

	public String getSelfWalletLoadCharges(String code, String amount) throws ClassNotFoundException {
		try {
			conn = createConnection("np_payment_gateway");
			String query = "SELECT ROUND(`base_charge`/100,2)+percentage*" + amount
					+ "/10000 FROM `limit_charges`.`charge_category_slabs` WHERE `category_code`="
					+ "'SELF_WALLET_LOAD_VIA_" + code + "_CHARGE_CASH_MODE' AND " + amount
					+ " BETWEEN `slab_from_amount`/100+1 AND `slab_to_amount`/100;";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();

		}
		return null;
	}

	public String getOrgCodeFromMobNum(String mobNum) throws ClassNotFoundException {
		try {
			conn = createConnection("master");
			String query = "SELECT o.code FROM `master`.`user` u JOIN `master`.`user_attribute` ua ON u.`id`="
					+ "ua.`user_id` JOIN master.organization o ON u.organization = o.id WHERE ua.`attr_value` = '"
					+ mobNum + "' AND u.`status` = 'ACTIVE' AND o.`status` = 'ACTIVE';";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();

		}
		return null;
	}

	public String getDeleteAccountDays() throws ClassNotFoundException {
		try {
			conn = createConnection("master");
			String query = "SELECT prop_value FROM `config`.`configuration` WHERE "
					+ "prop_key = 'novopay.min.allowed.days.to.delete.settlement.info';";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();

		}
		return null;
	}

	public String getSettlementCharge() throws ClassNotFoundException {
		try {
			conn = createConnection("master");
			String query = "SELECT `value` FROM np_actor.`platform_master_data` WHERE `code` = 'ACCOUNT_VALIDATION_CHARGE';";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();

		}
		return null;
	}

	public String getMaxAccounts() throws ClassNotFoundException {
		try {
			conn = createConnection("master");
			String query = "SELECT `value` FROM np_actor.`platform_master_data` WHERE `code` = 'MAX_ALLOWED_SETTLEMENT_ACCOUNTS';";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB..!");
			sqe.printStackTrace();

		}
		return null;
	}

	public void deleteAndInsertBioAuthDetails(String mobNum) throws ClassNotFoundException {
		try {
			conn = createConnection("master");
			String deleteQuery = "DELETE FROM `np_aepstxn`.`aeps_bio_auth_details` WHERE `agent_id_value` = '"
					+ getOrgCodeFromMobNum(mobNum) + "';";

			String insertQuery = "INSERT INTO `np_aepstxn`.`aeps_bio_auth_details` (`agent_id_type`, `agent_id_value`, `partner`, "
					+ "`novopay_ref_number`, `aadhaar`, `status`, `partner_response_code`, `uidai_token`, "
					+ "`external_ref_number`, `created_by`, `created_on`, `updated_by`, `updated_on`, `is_deleted`) "
					+ "VALUES('ORG_CODE','" + getOrgCodeFromMobNum(mobNum)
					+ "','INDUSIND','120011001100','abcd','SUCCESS','00',NULL,NULL,'" + mobNum + "',NOW(),'" + mobNum
					+ "',NOW(),'0');";

			stmt = conn.createStatement();
			stmt.executeUpdate(deleteQuery);
			System.out.println("Deleting all records for the org code: " + getOrgCodeFromMobNum(mobNum));
			stmt.executeUpdate(insertQuery);
			System.out.println("Inserting one record");
		} catch (SQLException sqe) {
			System.out.println("Error occurred");
		}
	}

	public void updateIfscMode(String ifsc, String mode) throws ClassNotFoundException {
		try {
			String num = "";
			if (mode.equalsIgnoreCase("IMPS")) {
				num = "1";
			} else if (mode.equalsIgnoreCase("NEFT")) {
				num = "0";
			}
			conn = createConnection("master");
			String query = "UPDATE `np_master`.`ifsc_master_new` SET `imps_supported` = '" + num
					+ "' WHERE `ifsc_code` = '" + ifsc + "';";
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
			System.out.println("Updating IFSC mode for " + ifsc + " as " + num);
		} catch (SQLException sqe) {
			System.out.println("Error executing query");
			sqe.printStackTrace();
		}
	}
}