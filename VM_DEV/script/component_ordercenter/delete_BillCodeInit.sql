DELETE FROM pub_bcr_candiattr WHERE pk_nbcr = '0001ZZ10000000008C5R';
DELETE FROM pub_bcr_elem WHERE pk_billcodebase in ( select pk_billcodebase from pub_bcr_RuleBase where nbcrcode = 'CO01' );
DELETE FROM pub_bcr_RuleBase WHERE nbcrcode = 'CO01';
DELETE FROM pub_bcr_nbcr WHERE pk_nbcr = '0001ZZ10000000008C5R';
DELETE FROM pub_bcr_OrgRela WHERE pk_billcodebase = '0001ZZ10000000008C5S';
DELETE FROM pub_bcr_RuleBase WHERE pk_billcodebase = '0001ZZ10000000008C5S';
DELETE FROM pub_bcr_elem WHERE pk_billcodeelem = '0001ZZ10000000008C5T';
DELETE FROM pub_bcr_elem WHERE pk_billcodeelem = '0001ZZ10000000008C5U';
DELETE FROM pub_bcr_elem WHERE pk_billcodeelem = '0001ZZ10000000008C5V';