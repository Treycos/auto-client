/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.autoclient.PVP_net;

import cz.autoclient.settings.Settings;

/**
 * Holds setting names and default values. This allows us to easily refactor settings whenever we want.
 * @author Jakub
 */
public enum Setnames {
  TEAMBUILDER_ENABLED("tb_enabled", false),
  //Whether the program should automatically start a game if possible
  TEAMBUILDER_AUTOSTART_ENABLED("tb_cap_autostart_enabled", false),
  INVITE_ENABLED("invite_enabled", false),
  TRAY_ICON_ENABLED("tray_enabled", true),
  TRAY_ICON_MINIMIZE("tray_minimize", false),
  /**
   * First summoner spell name (string or null)
   */
  BLIND_SUMMONER1("sumspell1", null),
  /**
   * Seconds summoner spell name (string or null)
   */
  BLIND_SUMMONER2("sumspell2", null),
  /**
   * Champion name to pick
   */
  BLIND_CHAMP_NAME(""),
  /**
   * Text to call after entering normal lobby
   */
  BLIND_CALL_TEXT(""),
  
  BLIND_MASTERY("masterypg", 0),
  BLIND_RUNE("runepg", 0),
  /** ARAM **/
  ARAM_ENABLED(false),
  /**NOTFICATONS**/
  NOTIF_APP_UPDATE_EXISTS_TRAYBALOON(true),

  AM_SAY(),
  AUTO_QUEUE_ENABLED(false),
  
  REMEMBER_PASSWORD(null),
  
  ENCRYPTION_USE_PW(false),
  ENCRYPTION_PW(null),
  ENCRYPTION_USE_HWID(true),
  //Automation settings
  PREVENT_CLIENT_MINIMIZE(false),
  
  START_AS_ADMIN(false),
  
  UPDATES_AUTOCHECK(true),
  UPDATES_AUTODOWNLOAD(false),
  UPDATES_IGNORE_BETAS(true),
  
  
  DEBUG_PRETEND_ARAM(false)
  //PA names
  
  , DEBUG_UPDATES_LOCAL(false)
  , DEBUG_UPDATES_LOCAL_PATH("C:\\MYSELF\\programing\\java\\AutoCall\\fakeupdates")
  ;
  
  public final String name;
  public final Object default_val;
  Setnames(String n, Object d) {
    name = n;
    default_val = d;    
  }
  Setnames(Object d) {
    name = this.name().toLowerCase();
    default_val = d;    
  }
  Setnames() {
    name = this.name().toLowerCase();
    default_val = null;    
  }
  public boolean getBoolean(Settings settings) {
    return settings.getBoolean(name, default_val instanceof Boolean?(Boolean)default_val:false);
  }
  public String getString(Settings settings) {
    return settings.getStringEquivalent(name);
  }
  
  public static void setDefaults(Settings settings) {
    for(Setnames set : Setnames.values()) {
      settings.setSettingDefault(set.name, set.default_val);
    }
  }
  
}
