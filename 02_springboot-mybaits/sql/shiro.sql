/*
 Navicat Premium Data Transfer

 Source Server         : MC
 Source Server Type    : Oracle
 Source Server Version : 110200
 Source Host           : 172.16.10.171:1521
 Source Schema         : DPACK

 Target Server Type    : MySQL
 Target Server Version : 50799
 File Encoding         : 65001

 Date: 18/06/2019 16:34:48
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for NE_AUTHORITY_CONTROLLERACTION
-- ----------------------------
DROP TABLE IF EXISTS `NE_AUTHORITY_CONTROLLERACTION`;
CREATE TABLE `NE_AUTHORITY_CONTROLLERACTION`  (
  `ID` varchar(32) NOT NULL,
  `CONTROLLER` varchar(50),
  `ACTION` varchar(50),
  `NAME` varchar(50),
  `ISMENU` varchar(2),
  `MENUPID` varchar(32),
  `MENUSEQ` decimal(65, 30),
  `REMARK` varchar(200),
  `SYSVERSION` decimal(65, 30),
  `CREATEUSER` varchar(10),
  `CREATEDATE` decimal(8, 0),
  `CREATETIME` decimal(6, 0),
  `LOGUSER` varchar(10),
  `LOGDATE` decimal(8, 0),
  `LOGTIME` decimal(6, 0),
  `TITLEIMG` varchar(32),
  PRIMARY KEY (`ID`)
);

-- ----------------------------
-- Records of NE_AUTHORITY_CONTROLLERACTION
-- ----------------------------
BEGIN;
INSERT INTO `NE_AUTHORITY_CONTROLLERACTION` VALUES ('783f40bc113a5f7ee053a80a10acb3d0', 'Subsidy', 'SaleDataList', '补贴数据查询', '0', '783ef8b3a06728dae053a80a10acacb6', 201, NULL, 4, 'a6924', 20181015, 140733, 'a6924', 20190103, 114017, NULL), ('783f40bc113b5f7ee053a80a10acb3d0', 'Subsidy', 'SaleOldSystem', '补贴旧系统入口', '0', '783ef8b3a06728dae053a80a10acacb6', 202, NULL, 4, 'a6924', 20181015, 140733, 'a6924', 20190103, 114017, NULL), ('783f40bc113c5f7ee053a80a10acb3d0', 'Subsidy', 'SaleInvoiceDataImport', '销售发票数据导入', '0', '783ef8b3a06728dae053a80a10acacb6', 203, NULL, 4, 'a6924', 20181015, 140733, 'a6924', 20190103, 114017, NULL), ('783f40bc113d5f7ee053a80a10acb3d0', 'Subsidy', 'SaleDataDetail', NULL, '1', NULL, 210, NULL, 4, 'a6924', 20181015, 140733, 'a6924', 20190103, 114017, NULL), ('783f40bc113e5f7ee053a80a10acb3d0', 'Subsidy', 'SaleExportData', NULL, '1', NULL, 211, NULL, 4, 'a6924', 20181015, 140733, 'a6924', 20190103, 114017, NULL), ('783f40bc113f5f7ee053a80a10acb3d0', 'Subsidy', 'SaleSaveInvoiceFileData', NULL, '1', NULL, 212, NULL, 4, 'a6924', 20181015, 140733, 'a6924', 20190103, 114017, NULL), ('783f40bc11405f7ee053a80a10acb3d0', 'Subsidy', 'EICSysBaseDataList', '三电基础车型数据', '0', '783ef8b3a06728dae053a80a10acacb6', 220, NULL, 4, 'a6924', 20181015, 140733, 'a6924', 20190103, 114017, NULL), ('783f40bc11415f7ee053a80a10acb3d0', 'Subsidy', 'EICSysBaseDataEdit', '三电基础车型数据(新增)', '0', '783ef8b3a06728dae053a80a10acacb6', 221, NULL, 4, 'a6924', 20181015, 140733, 'a6924', 20190103, 114017, NULL), ('783f40bc11425f7ee053a80a10acb3d0', 'Subsidy', 'EICSysBaseDataImport', '三电基础车型数据(导入)', '0', '783ef8b3a06728dae053a80a10acacb6', 222, NULL, 4, 'a6924', 20181015, 140733, 'a6924', 20190103, 114017, NULL), ('783f40bc11435f7ee053a80a10acb3d0', 'Subsidy', 'EICDelSysBaseData', NULL, '1', NULL, 230, NULL, 4, 'a6924', 20181015, 140733, 'a6924', 20190103, 114017, NULL), ('783f40bc11445f7ee053a80a10acb3d0', 'Subsidy', 'EICSaveSysBaseFileData', NULL, '1', NULL, 231, NULL, 4, 'a6924', 20181015, 140733, 'a6924', 20190103, 114017, NULL), ('783f40bc11455f7ee053a80a10acb3d0', 'Subsidy', 'EICInvoiceDataList', '三电采购发票数据', '0', '783ef8b3a06728dae053a80a10acacb6', 240, NULL, 4, 'a6924', 20181015, 140733, 'a6924', 20190103, 114017, NULL), ('783f40bc11465f7ee053a80a10acb3d0', 'Subsidy', 'EICInvoiceDataImport', '三电采购发票数据(导入)', '0', '783ef8b3a06728dae053a80a10acacb6', 241, NULL, 4, 'a6924', 20181015, 140733, 'a6924', 20190103, 114017, NULL), ('783f40bc11475f7ee053a80a10acb3d0', 'Subsidy', 'EICInvoiceDataEdit', NULL, '1', NULL, 250, NULL, 4, 'a6924', 20181015, 140733, 'a6924', 20190103, 114017, NULL), ('783f40bc11485f7ee053a80a10acb3d0', 'Subsidy', 'EICSaveInvoiceData', NULL, '1', NULL, 251, NULL, 4, 'a6924', 20181015, 140733, 'a6924', 20190103, 114017, NULL), ('783f40bc11495f7ee053a80a10acb3d0', 'Subsidy', 'EICSaveInvoiceFileData', NULL, '1', NULL, 252, NULL, 4, 'a6924', 20181015, 140733, 'a6924', 20190103, 114017, NULL), ('783f40bc114a5f7ee053a80a10acb3d0', 'Subsidy', 'EICEmptyInvoiceData', NULL, '1', NULL, 253, NULL, 4, 'a6924', 20181015, 140733, 'a6924', 20190103, 114017, NULL), ('783f40bc114b5f7ee053a80a10acb3d0', 'Subsidy', 'EICExportInvoiceData', NULL, '1', NULL, 254, NULL, 4, 'a6924', 20181015, 140733, 'a6924', 20190103, 114017, NULL), ('783f40bc114c5f7ee053a80a10acb3d0', 'Subsidy', 'EICSaveSysBaseData', NULL, '1', NULL, 232, NULL, 4, 'a6924', 20181015, 140733, 'a6924', 20190103, 114017, NULL), ('783f40bc114d5f7ee053a80a10acb3d0', NULL, NULL, '系统管理', '0', NULL, 800, NULL, 4, 'a6924', 20181015, 140733, 'a6924', 20190103, 114017, '&#xe62d;'), ('783f40bc114e5f7ee053a80a10acb3d0', 'Auth', 'ActionMgmt', 'Action管理', '0', '783f40bc114d5f7ee053a80a10acb3d0', 801, NULL, 3, 'a6924', 20181015, 141043, 'a6924', 20190103, 114017, NULL), ('783f40bc114f5f7ee053a80a10acb3d0', 'Auth', 'GroupMgmt', 'Group管理', '0', '783f40bc114d5f7ee053a80a10acb3d0', 802, NULL, 3, 'a6924', 20181015, 141043, 'a6924', 20190103, 114017, NULL), ('783f40bc11505f7ee053a80a10acb3d0', 'Auth', 'AuthMemo', '权限说明', '0', '783f40bc114d5f7ee053a80a10acb3d0', 803, NULL, 3, 'a6924', 20181015, 141043, 'a6924', 20190103, 114017, NULL), ('783f40bc11515f7ee053a80a10acb3d0', 'Auth', 'Log', '日志查看', '0', '783f40bc114d5f7ee053a80a10acb3d0', 804, NULL, 3, 'a6924', 20181015, 141043, 'a6924', 20190103, 114017, NULL), ('783f40bc11525f7ee053a80a10acb3d0', 'Auth', 'EditGroupAction', NULL, '1', NULL, 850, NULL, 3, 'a6924', 20181015, 141043, 'a6924', 20190103, 114017, NULL), ('783f40bc11535f7ee053a80a10acb3d0', 'Auth', 'EditGroupUser', NULL, '1', NULL, 851, NULL, 3, 'a6924', 20181015, 141043, 'a6924', 20190103, 114017, NULL), ('783f40bc11545f7ee053a80a10acb3d0', 'Auth', 'SaveAction', NULL, '1', NULL, 852, NULL, 3, 'a6924', 20181015, 141043, 'a6924', 20190103, 114017, NULL), ('783f40bc11555f7ee053a80a10acb3d0', 'Auth', 'SaveGroup', NULL, '1', NULL, 853, NULL, 3, 'a6924', 20181015, 141043, 'a6924', 20190103, 114017, NULL), ('783f40bc11565f7ee053a80a10acb3d0', 'Auth', 'SaveGroupAction', NULL, '1', NULL, 854, NULL, 3, 'a6924', 20181015, 141043, 'a6924', 20190103, 114017, NULL), ('783f40bc11575f7ee053a80a10acb3d0', 'Auth', 'SaveGroupUser', NULL, '1', NULL, 855, NULL, 3, 'a6924', 20181015, 141043, 'a6924', 20190103, 114017, NULL), ('7e86854eb1ef099ce053a80a10acc4f4', 'Subsidy', 'SaleSyncInvoiceData', NULL, '1', NULL, 213, NULL, 1, 'a6924', 20190103, 114017, 'a6924', 20190103, 114017, NULL), ('783ef8b3a05d28dae053a80a10acacb6', 'Home', 'Index', NULL, '1', NULL, 0, NULL, 10, 'a6924', 20181015, 134724, 'a6924', 20190103, 114017, NULL), ('783ef8b3a05e28dae053a80a10acacb6', 'Home', 'Welcome', NULL, '1', NULL, 1, NULL, 9, 'a6924', 20181015, 134805, 'a6924', 20190103, 114017, NULL), ('783ef8b3a05f28dae053a80a10acacb6', NULL, NULL, '车间三电铭牌', '0', NULL, 100, NULL, 8, 'a6924', 20181015, 135106, 'a6924', 20190103, 114017, '&#xe613;'), ('783ef8b3a06028dae053a80a10acacb6', 'CardInfo', 'CardQuery', '铭牌首页', '0', '783ef8b3a05f28dae053a80a10acacb6', 101, NULL, 7, 'a6924', 20181015, 135157, 'a6924', 20190103, 114017, NULL), ('783ef8b3a06128dae053a80a10acacb6', 'CardInfo', 'AddEditCard', '新增铭牌', '0', '783ef8b3a05f28dae053a80a10acacb6', 102, NULL, 6, 'a6924', 20181015, 135420, 'a6924', 20190103, 114017, NULL), ('783ef8b3a06228dae053a80a10acacb6', 'CardInfo', 'CopyCard', '复制铭牌', '0', '783ef8b3a05f28dae053a80a10acacb6', 103, NULL, 6, 'a6924', 20181015, 135420, 'a6924', 20190103, 114017, NULL), ('783ef8b3a06328dae053a80a10acacb6', 'CardInfo', 'Export', '信息导出', '0', '783ef8b3a05f28dae053a80a10acacb6', 104, NULL, 6, 'a6924', 20181015, 135420, 'a6924', 20190103, 114017, NULL), ('783ef8b3a06428dae053a80a10acacb6', 'CardInfo', 'ReadLog', '日志查看', '0', '783ef8b3a05f28dae053a80a10acacb6', 105, NULL, 6, 'a6924', 20181015, 135420, 'a6924', 20190103, 114017, NULL), ('783ef8b3a06528dae053a80a10acacb6', 'CardInfo', 'UserMgmt', '权限管理', '0', '783ef8b3a05f28dae053a80a10acacb6', 106, NULL, 6, 'a6924', 20181015, 135420, 'a6924', 20190103, 114017, NULL), ('783ef8b3a06628dae053a80a10acacb6', 'CardInfo', 'Help', '帮助文档', '0', '783ef8b3a05f28dae053a80a10acacb6', 107, NULL, 6, 'a6924', 20181015, 135420, 'a6924', 20190103, 114017, NULL), ('783ef8b3a06728dae053a80a10acacb6', NULL, NULL, '新能源补贴', '0', NULL, 200, NULL, 5, 'a6924', 20181015, 135449, 'a6924', 20190103, 114017, '&#xe620;');
COMMIT;

-- ----------------------------
-- Table structure for NE_AUTHORITY_GROUP
-- ----------------------------
DROP TABLE IF EXISTS `NE_AUTHORITY_GROUP`;
CREATE TABLE `NE_AUTHORITY_GROUP`  (
  `ID` varchar(32) NOT NULL,
  `NAME` varchar(50),
  `REMARK` varchar(200),
  `SYSVERSION` decimal(65, 30),
  `CREATEUSER` varchar(10),
  `CREATEDATE` decimal(8, 0),
  `CREATETIME` decimal(6, 0),
  `LOGUSER` varchar(10),
  `LOGDATE` decimal(8, 0),
  `LOGTIME` decimal(6, 0),
  PRIMARY KEY (`ID`)
);

-- ----------------------------
-- Records of NE_AUTHORITY_GROUP
-- ----------------------------
BEGIN;
INSERT INTO `NE_AUTHORITY_GROUP` VALUES ('783f40bc11585f7ee053a80a10acb3d0', '管理员', NULL, 4, 'a6924', 20181015, 141157, 'a6924', 20181023, 134030), ('783f40bc11595f7ee053a80a10acb3d0', '财务(新能源补贴)', NULL, 4, 'a6924', 20181015, 141157, 'a6924', 20181023, 134030), ('783f40bc115a5f7ee053a80a10acb3d0', '采购(新能源补贴)', NULL, 4, 'a6924', 20181015, 141157, 'a6924', 20181023, 134030), ('783f40bc115b5f7ee053a80a10acb3d0', '技术(新能源补贴)', NULL, 4, 'a6924', 20181015, 141157, 'a6924', 20181023, 134030), ('787d07dbe67f6e2ce053a70a10acd44c', '销售(新能源补贴)', NULL, 3, 'a6924', 20181018, 155006, 'a6924', 20181023, 134030);
COMMIT;

-- ----------------------------
-- Table structure for NE_AUTHORITY_U_GROUPACTION
-- ----------------------------
DROP TABLE IF EXISTS `NE_AUTHORITY_U_GROUPACTION`;
CREATE TABLE `NE_AUTHORITY_U_GROUPACTION`  (
  `GROUP_ID` varchar(32) NOT NULL,
  `CONTROLLERACTION_ID` varchar(32) NOT NULL,
  `SYSVERSION` decimal(65, 30),
  `CREATEUSER` varchar(10),
  `CREATEDATE` decimal(8, 0),
  `CREATETIME` decimal(6, 0),
  `LOGUSER` varchar(10),
  `LOGDATE` decimal(8, 0),
  `LOGTIME` decimal(6, 0),
  PRIMARY KEY (`GROUP_ID`, `CONTROLLERACTION_ID`)
);

-- ----------------------------
-- Records of NE_AUTHORITY_U_GROUPACTION
-- ----------------------------
BEGIN;
INSERT INTO `NE_AUTHORITY_U_GROUPACTION` VALUES ('783f40bc11595f7ee053a80a10acb3d0', '783ef8b3a05d28dae053a80a10acacb6', 1, 'a6924', 20190103, 114101, 'a6924', 20190103, 114101), ('783f40bc11595f7ee053a80a10acb3d0', '783ef8b3a05e28dae053a80a10acacb6', 1, 'a6924', 20190103, 114101, 'a6924', 20190103, 114101), ('783f40bc11595f7ee053a80a10acb3d0', '783ef8b3a06728dae053a80a10acacb6', 1, 'a6924', 20190103, 114101, 'a6924', 20190103, 114101), ('783f40bc11595f7ee053a80a10acb3d0', '783f40bc113c5f7ee053a80a10acb3d0', 1, 'a6924', 20190103, 114101, 'a6924', 20190103, 114101), ('783f40bc11595f7ee053a80a10acb3d0', '783f40bc113f5f7ee053a80a10acb3d0', 1, 'a6924', 20190103, 114101, 'a6924', 20190103, 114101), ('783f40bc11595f7ee053a80a10acb3d0', '7e86854eb1ef099ce053a80a10acc4f4', 1, 'a6924', 20190103, 114101, 'a6924', 20190103, 114101), ('787d07dbe67f6e2ce053a70a10acd44c', '783ef8b3a05e28dae053a80a10acacb6', 1, 'a6924', 20181018, 155240, 'a6924', 20181018, 155240), ('787d07dbe67f6e2ce053a70a10acd44c', '783ef8b3a06728dae053a80a10acacb6', 1, 'a6924', 20181018, 155240, 'a6924', 20181018, 155240), ('787d07dbe67f6e2ce053a70a10acd44c', '783f40bc113a5f7ee053a80a10acb3d0', 1, 'a6924', 20181018, 155240, 'a6924', 20181018, 155240), ('787d07dbe67f6e2ce053a70a10acd44c', '783f40bc113b5f7ee053a80a10acb3d0', 1, 'a6924', 20181018, 155240, 'a6924', 20181018, 155240), ('787d07dbe67f6e2ce053a70a10acd44c', '783f40bc113d5f7ee053a80a10acb3d0', 1, 'a6924', 20181018, 155240, 'a6924', 20181018, 155240), ('787d07dbe67f6e2ce053a70a10acd44c', '783f40bc113e5f7ee053a80a10acb3d0', 1, 'a6924', 20181018, 155240, 'a6924', 20181018, 155240), ('783f40bc11585f7ee053a80a10acb3d0', '783ef8b3a05d28dae053a80a10acacb6', 1, 'a6924', 20190103, 114028, 'a6924', 20190103, 114028), ('783f40bc11585f7ee053a80a10acb3d0', '783ef8b3a05e28dae053a80a10acacb6', 1, 'a6924', 20190103, 114028, 'a6924', 20190103, 114028), ('783f40bc11585f7ee053a80a10acb3d0', '783ef8b3a05f28dae053a80a10acacb6', 1, 'a6924', 20190103, 114028, 'a6924', 20190103, 114028), ('783f40bc115a5f7ee053a80a10acb3d0', '783ef8b3a05d28dae053a80a10acacb6', 1, 'a6924', 20181015, 142322, 'a6924', 20181015, 142322), ('783f40bc115a5f7ee053a80a10acb3d0', '783ef8b3a05e28dae053a80a10acacb6', 1, 'a6924', 20181015, 142322, 'a6924', 20181015, 142322), ('783f40bc115a5f7ee053a80a10acb3d0', '783ef8b3a06728dae053a80a10acacb6', 1, 'a6924', 20181015, 142322, 'a6924', 20181015, 142322), ('783f40bc115a5f7ee053a80a10acb3d0', '783f40bc11455f7ee053a80a10acb3d0', 1, 'a6924', 20181015, 142322, 'a6924', 20181015, 142322), ('783f40bc115a5f7ee053a80a10acb3d0', '783f40bc11465f7ee053a80a10acb3d0', 1, 'a6924', 20181015, 142322, 'a6924', 20181015, 142322), ('783f40bc115a5f7ee053a80a10acb3d0', '783f40bc11475f7ee053a80a10acb3d0', 1, 'a6924', 20181015, 142322, 'a6924', 20181015, 142322), ('783f40bc115a5f7ee053a80a10acb3d0', '783f40bc11485f7ee053a80a10acb3d0', 1, 'a6924', 20181015, 142322, 'a6924', 20181015, 142322), ('783f40bc115a5f7ee053a80a10acb3d0', '783f40bc11495f7ee053a80a10acb3d0', 1, 'a6924', 20181015, 142322, 'a6924', 20181015, 142322), ('783f40bc115a5f7ee053a80a10acb3d0', '783f40bc114a5f7ee053a80a10acb3d0', 1, 'a6924', 20181015, 142322, 'a6924', 20181015, 142322), ('783f40bc115a5f7ee053a80a10acb3d0', '783f40bc114b5f7ee053a80a10acb3d0', 1, 'a6924', 20181015, 142322, 'a6924', 20181015, 142322), ('783f40bc11585f7ee053a80a10acb3d0', '783ef8b3a06028dae053a80a10acacb6', 1, 'a6924', 20190103, 114028, 'a6924', 20190103, 114028), ('783f40bc11585f7ee053a80a10acb3d0', '783ef8b3a06128dae053a80a10acacb6', 1, 'a6924', 20190103, 114028, 'a6924', 20190103, 114028), ('783f40bc11585f7ee053a80a10acb3d0', '783ef8b3a06228dae053a80a10acacb6', 1, 'a6924', 20190103, 114028, 'a6924', 20190103, 114028), ('783f40bc11585f7ee053a80a10acb3d0', '783ef8b3a06328dae053a80a10acacb6', 1, 'a6924', 20190103, 114028, 'a6924', 20190103, 114028), ('783f40bc11585f7ee053a80a10acb3d0', '783ef8b3a06428dae053a80a10acacb6', 1, 'a6924', 20190103, 114028, 'a6924', 20190103, 114028), ('783f40bc11585f7ee053a80a10acb3d0', '783ef8b3a06528dae053a80a10acacb6', 1, 'a6924', 20190103, 114028, 'a6924', 20190103, 114028), ('783f40bc11585f7ee053a80a10acb3d0', '783ef8b3a06628dae053a80a10acacb6', 1, 'a6924', 20190103, 114028, 'a6924', 20190103, 114028), ('787d07dbe67f6e2ce053a70a10acd44c', '783ef8b3a05d28dae053a80a10acacb6', 1, 'a6924', 20181018, 155240, 'a6924', 20181018, 155240), ('783f40bc11585f7ee053a80a10acb3d0', '783ef8b3a06728dae053a80a10acacb6', 1, 'a6924', 20190103, 114028, 'a6924', 20190103, 114028), ('783f40bc11585f7ee053a80a10acb3d0', '783f40bc113a5f7ee053a80a10acb3d0', 1, 'a6924', 20190103, 114028, 'a6924', 20190103, 114028), ('783f40bc11585f7ee053a80a10acb3d0', '783f40bc113b5f7ee053a80a10acb3d0', 1, 'a6924', 20190103, 114028, 'a6924', 20190103, 114028), ('783f40bc11585f7ee053a80a10acb3d0', '783f40bc113c5f7ee053a80a10acb3d0', 1, 'a6924', 20190103, 114028, 'a6924', 20190103, 114028), ('783f40bc11585f7ee053a80a10acb3d0', '783f40bc113d5f7ee053a80a10acb3d0', 1, 'a6924', 20190103, 114028, 'a6924', 20190103, 114028), ('783f40bc11585f7ee053a80a10acb3d0', '783f40bc113e5f7ee053a80a10acb3d0', 1, 'a6924', 20190103, 114028, 'a6924', 20190103, 114028), ('783f40bc11585f7ee053a80a10acb3d0', '783f40bc113f5f7ee053a80a10acb3d0', 1, 'a6924', 20190103, 114028, 'a6924', 20190103, 114028), ('783f40bc11585f7ee053a80a10acb3d0', '7e86854eb1ef099ce053a80a10acc4f4', 1, 'a6924', 20190103, 114028, 'a6924', 20190103, 114028), ('783f40bc11585f7ee053a80a10acb3d0', '783f40bc11405f7ee053a80a10acb3d0', 1, 'a6924', 20190103, 114028, 'a6924', 20190103, 114028), ('783f40bc11585f7ee053a80a10acb3d0', '783f40bc11415f7ee053a80a10acb3d0', 1, 'a6924', 20190103, 114028, 'a6924', 20190103, 114028), ('783f40bc11585f7ee053a80a10acb3d0', '783f40bc11425f7ee053a80a10acb3d0', 1, 'a6924', 20190103, 114028, 'a6924', 20190103, 114028), ('783f40bc11585f7ee053a80a10acb3d0', '783f40bc11435f7ee053a80a10acb3d0', 1, 'a6924', 20190103, 114028, 'a6924', 20190103, 114028), ('783f40bc11585f7ee053a80a10acb3d0', '783f40bc11445f7ee053a80a10acb3d0', 1, 'a6924', 20190103, 114028, 'a6924', 20190103, 114028), ('783f40bc11585f7ee053a80a10acb3d0', '783f40bc114c5f7ee053a80a10acb3d0', 1, 'a6924', 20190103, 114028, 'a6924', 20190103, 114028), ('783f40bc11585f7ee053a80a10acb3d0', '783f40bc11455f7ee053a80a10acb3d0', 1, 'a6924', 20190103, 114028, 'a6924', 20190103, 114028), ('783f40bc11585f7ee053a80a10acb3d0', '783f40bc11465f7ee053a80a10acb3d0', 1, 'a6924', 20190103, 114028, 'a6924', 20190103, 114028), ('783f40bc11585f7ee053a80a10acb3d0', '783f40bc11475f7ee053a80a10acb3d0', 1, 'a6924', 20190103, 114028, 'a6924', 20190103, 114028), ('783f40bc11585f7ee053a80a10acb3d0', '783f40bc11485f7ee053a80a10acb3d0', 1, 'a6924', 20190103, 114028, 'a6924', 20190103, 114028), ('783f40bc11585f7ee053a80a10acb3d0', '783f40bc11495f7ee053a80a10acb3d0', 1, 'a6924', 20190103, 114028, 'a6924', 20190103, 114028), ('783f40bc11585f7ee053a80a10acb3d0', '783f40bc114a5f7ee053a80a10acb3d0', 1, 'a6924', 20190103, 114028, 'a6924', 20190103, 114028), ('783f40bc11585f7ee053a80a10acb3d0', '783f40bc114b5f7ee053a80a10acb3d0', 1, 'a6924', 20190103, 114028, 'a6924', 20190103, 114028), ('783f40bc11585f7ee053a80a10acb3d0', '783f40bc114d5f7ee053a80a10acb3d0', 1, 'a6924', 20190103, 114028, 'a6924', 20190103, 114028), ('783f40bc11585f7ee053a80a10acb3d0', '783f40bc114e5f7ee053a80a10acb3d0', 1, 'a6924', 20190103, 114028, 'a6924', 20190103, 114028), ('783f40bc11585f7ee053a80a10acb3d0', '783f40bc114f5f7ee053a80a10acb3d0', 1, 'a6924', 20190103, 114028, 'a6924', 20190103, 114028), ('783f40bc11585f7ee053a80a10acb3d0', '783f40bc11505f7ee053a80a10acb3d0', 1, 'a6924', 20190103, 114028, 'a6924', 20190103, 114028), ('783f40bc11585f7ee053a80a10acb3d0', '783f40bc11515f7ee053a80a10acb3d0', 1, 'a6924', 20190103, 114028, 'a6924', 20190103, 114028), ('783f40bc11585f7ee053a80a10acb3d0', '783f40bc11525f7ee053a80a10acb3d0', 1, 'a6924', 20190103, 114028, 'a6924', 20190103, 114028), ('783f40bc11585f7ee053a80a10acb3d0', '783f40bc11535f7ee053a80a10acb3d0', 1, 'a6924', 20190103, 114028, 'a6924', 20190103, 114028), ('783f40bc11585f7ee053a80a10acb3d0', '783f40bc11545f7ee053a80a10acb3d0', 1, 'a6924', 20190103, 114028, 'a6924', 20190103, 114028), ('783f40bc11585f7ee053a80a10acb3d0', '783f40bc11555f7ee053a80a10acb3d0', 1, 'a6924', 20190103, 114028, 'a6924', 20190103, 114028), ('783f40bc11585f7ee053a80a10acb3d0', '783f40bc11565f7ee053a80a10acb3d0', 1, 'a6924', 20190103, 114028, 'a6924', 20190103, 114028), ('783f40bc11585f7ee053a80a10acb3d0', '783f40bc11575f7ee053a80a10acb3d0', 1, 'a6924', 20190103, 114028, 'a6924', 20190103, 114028), ('783f40bc115b5f7ee053a80a10acb3d0', '783ef8b3a05d28dae053a80a10acacb6', 1, 'a6924', 20181015, 142541, 'a6924', 20181015, 142541), ('783f40bc115b5f7ee053a80a10acb3d0', '783ef8b3a05e28dae053a80a10acacb6', 1, 'a6924', 20181015, 142541, 'a6924', 20181015, 142541), ('783f40bc115b5f7ee053a80a10acb3d0', '783ef8b3a06728dae053a80a10acacb6', 1, 'a6924', 20181015, 142541, 'a6924', 20181015, 142541), ('783f40bc115b5f7ee053a80a10acb3d0', '783f40bc11405f7ee053a80a10acb3d0', 1, 'a6924', 20181015, 142541, 'a6924', 20181015, 142541), ('783f40bc115b5f7ee053a80a10acb3d0', '783f40bc11415f7ee053a80a10acb3d0', 1, 'a6924', 20181015, 142541, 'a6924', 20181015, 142541), ('783f40bc115b5f7ee053a80a10acb3d0', '783f40bc11425f7ee053a80a10acb3d0', 1, 'a6924', 20181015, 142541, 'a6924', 20181015, 142541), ('783f40bc115b5f7ee053a80a10acb3d0', '783f40bc11435f7ee053a80a10acb3d0', 1, 'a6924', 20181015, 142541, 'a6924', 20181015, 142541), ('783f40bc115b5f7ee053a80a10acb3d0', '783f40bc11445f7ee053a80a10acb3d0', 1, 'a6924', 20181015, 142541, 'a6924', 20181015, 142541), ('783f40bc115b5f7ee053a80a10acb3d0', '783f40bc114c5f7ee053a80a10acb3d0', 1, 'a6924', 20181015, 142541, 'a6924', 20181015, 142541);
COMMIT;

-- ----------------------------
-- Table structure for NE_AUTHORITY_U_GROUPUSER
-- ----------------------------
DROP TABLE IF EXISTS `NE_AUTHORITY_U_GROUPUSER`;
CREATE TABLE `NE_AUTHORITY_U_GROUPUSER`  (
  `GROUP_ID` varchar(32) NOT NULL,
  `USER_USERCODE` varchar(10) NOT NULL,
  `USER_USERNAME` varchar(20),
  `SYSVERSION` decimal(65, 30),
  `CREATEUSER` varchar(10),
  `CREATEDATE` decimal(8, 0),
  `CREATETIME` decimal(6, 0),
  `LOGUSER` varchar(10),
  `LOGDATE` decimal(8, 0),
  `LOGTIME` decimal(6, 0),
  `USER_FIELD0` varchar(200),
  `USER_FIELD1` varchar(200),
  `USER_FIELD2` varchar(200),
  `USER_FIELD3` varchar(200),
  `USER_FIELD4` varchar(200),
  PRIMARY KEY (`GROUP_ID`, `USER_USERCODE`)
);

-- ----------------------------
-- Records of NE_AUTHORITY_U_GROUPUSER
-- ----------------------------
BEGIN;
INSERT INTO `NE_AUTHORITY_U_GROUPUSER` VALUES ('787d07dbe67f6e2ce053a70a10acd44c', 'a2898', '庞哲', 1, 'a6924', 20181207, 92654, 'a6924', 20181207, 92654, NULL, NULL, NULL, NULL, NULL), ('787d07dbe67f6e2ce053a70a10acd44c', 'a6948', '谢思超', 1, 'a6924', 20181207, 92654, 'a6924', 20181207, 92654, NULL, NULL, NULL, NULL, NULL), ('783f40bc115a5f7ee053a80a10acb3d0', 'a8176', '洪碧峰', 1, 'a6924', 20181015, 162913, 'a6924', 20181015, 162913, '1', 'motor&dynamo', NULL, NULL, NULL), ('783f40bc115a5f7ee053a80a10acb3d0', 'a2249', '张炜强', 1, 'a6924', 20181015, 162913, 'a6924', 20181015, 162913, '1', 'battery', NULL, NULL, NULL), ('783f40bc115a5f7ee053a80a10acb3d0', 'a0813', '郭启智', 1, 'a6924', 20181015, 162913, 'a6924', 20181015, 162913, '3', 'battery&sc&motor&dynamo&fuelcell', NULL, NULL, NULL), ('783f40bc115a5f7ee053a80a10acb3d0', 'a2328', '刘鹏程', 1, 'a6924', 20181015, 162913, 'a6924', 20181015, 162913, '7', 'motor&dynamo', NULL, NULL, NULL), ('783f40bc115a5f7ee053a80a10acb3d0', 'a6294', '彭博涵', 1, 'a6924', 20181015, 162913, 'a6924', 20181015, 162913, '7', 'battery', NULL, NULL, NULL), ('787d07dbe67f6e2ce053a70a10acd44c', 'a2983', '康东逵', 1, 'a6924', 20181207, 92654, 'a6924', 20181207, 92654, NULL, NULL, NULL, NULL, NULL), ('787d07dbe67f6e2ce053a70a10acd44c', 'a4979', '钱宇翔', 1, 'a6924', 20181207, 92654, 'a6924', 20181207, 92654, NULL, NULL, NULL, NULL, NULL), ('783f40bc115b5f7ee053a80a10acb3d0', 'a1452', '杨英慧', 1, 'a6924', 20181015, 142159, 'a6924', 20181015, 142159, NULL, NULL, NULL, NULL, NULL), ('783f40bc115b5f7ee053a80a10acb3d0', 'a1875', '庄唯', 1, 'a6924', 20181015, 142159, 'a6924', 20181015, 142159, NULL, NULL, NULL, NULL, NULL), ('783f40bc115b5f7ee053a80a10acb3d0', 'a4630', '刘元城', 1, 'a6924', 20181015, 142159, 'a6924', 20181015, 142159, NULL, NULL, NULL, NULL, NULL), ('783f40bc115b5f7ee053a80a10acb3d0', 'a3205', '陈银川', 1, 'a6924', 20181015, 142159, 'a6924', 20181015, 142159, NULL, NULL, NULL, NULL, NULL), ('783f40bc115b5f7ee053a80a10acb3d0', 'a2888', '李永良', 1, 'a6924', 20181015, 142159, 'a6924', 20181015, 142159, NULL, NULL, NULL, NULL, NULL), ('783f40bc115b5f7ee053a80a10acb3d0', 'a2985', '周一鸿', 1, 'a6924', 20181015, 142159, 'a6924', 20181015, 142159, NULL, NULL, NULL, NULL, NULL), ('783f40bc11585f7ee053a80a10acb3d0', 'a6924', '陈庆林', 1, 'a6924', 20181015, 142739, 'a6924', 20181015, 142739, '1&2&3&4&5&7&8', 'common&battery&sc&motor&dynamo&fuelcell', NULL, NULL, NULL), ('783f40bc11585f7ee053a80a10acb3d0', 'a2889', '徐立', 1, 'a6924', 20181015, 142739, 'a6924', 20181015, 142739, '1&2&3&4&5&7&8', 'common&battery&sc&motor&dynamo&fuelcell', NULL, NULL, NULL), ('783f40bc11595f7ee053a80a10acb3d0', 'a4684', '徐司晨', 1, 'a6924', 20181203, 102852, 'a6924', 20181203, 102852, NULL, NULL, NULL, NULL, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
