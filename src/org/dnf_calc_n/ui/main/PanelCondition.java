package org.dnf_calc_n.ui.main;

import org.dnf_calc_n.Common;
import org.json.simple.JSONObject;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;

public class PanelCondition extends JPanel {

    Common common = new Common();
    HashMap<String, Font> mapFont;
    JPanel root;
    Color bgColor = new Color(50, 46, 52);

    public PanelCondition(JPanel root){
        this.mapFont = common.loadFont();
        this.root = root;
        this.setBackground(bgColor);
        this.setBounds(470, 10, 450, 150);
        this.setLayout(null);
        root.add(this);
    }

    ArrayList<String> listToggle = new ArrayList<>();
    ArrayList<String> listGauge = new ArrayList<>();

    int[][] grid = {
            {0, 0},{0, 1},{0, 2},{0, 3},{0, 4},
            {1, 0},{1, 1},{1, 2},{1, 3},{1, 4},
            {2, 0},{2, 1},{2, 2},{2, 3},{2, 4},
            {3, 0},{3, 1},{3, 2},{3, 3},{3, 4},
            {4, 0},{4, 1},{4, 2},{4, 3},{4, 4}
    };
    HashMap<String, JLabel> labelConditions = new HashMap<>();
    HashMap<String, JCheckBox> widgetToggle = new HashMap<>();
    HashMap<String, JComboBox<String>> widgetGauge = new HashMap<>();
    HashMap<String, String> mapSelectCondition = new HashMap<>();

    public void setConditions(JSONObject conditionJson){
        resetConditionPanel();
        System.out.println(conditionJson.toJSONString());
        int index = 0;
        for(Object k : conditionJson.keySet()){
            int[] nowGrid = grid[index];
            String key = (String) k;
            JLabel nowLabel = new JLabel(key);
            nowLabel.setForeground(Color.WHITE);
            nowLabel.setFont(mapFont.get("normal_bold"));
            nowLabel.setBorder(new EmptyBorder(0, 0, 0, 0));
            nowLabel.setBounds(5+nowGrid[0]*120, 3+nowGrid[1]*25, 90, 25);
            this.add(nowLabel);
            labelConditions.put(key, nowLabel);
            if("tg".equals(conditionJson.get(key))){
                // 토글형
                listToggle.add(key);
                JCheckBox nowCheck = new JCheckBox();
                nowCheck.setBackground(bgColor);
                nowCheck.setBounds(95+nowGrid[0]*120, 3+nowGrid[1]*25, 30, 25);
                if(mapSelectCondition.get(key) == null){
                    mapSelectCondition.put(key, "true");
                    nowCheck.setSelected(true);
                }else if("true".equals(mapSelectCondition.get(key))){
                    nowCheck.setSelected(true);
                }else if("false".equals(mapSelectCondition.get(key))){
                    nowCheck.setSelected(false);
                }

                nowCheck.addItemListener(new ItemListener() {
                    public void itemStateChanged(ItemEvent e) {
                        if(nowCheck.isSelected()){
                            mapSelectCondition.put(key, "true");
                        }else{
                            mapSelectCondition.put(key, "false");
                        }
                    }
                });
                this.add(nowCheck);
                widgetToggle.put(key, nowCheck);
            }else{
                // 게이지형
                listGauge.add(key);
                mapSelectCondition.put(key, "0");
            }
            index++;
        }
        this.updateUI();
    }

    private void resetConditionPanel(){
        listToggle.clear();
        listGauge.clear();
        labelConditions.clear();
        widgetToggle.clear();
        widgetGauge.clear();
        this.removeAll();

    }

    public HashMap<String, String> getMapSelectCondition(){
        return mapSelectCondition;
    }

}
