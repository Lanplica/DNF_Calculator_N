package org.dnf_calc_n.ui.main;

import org.dnf_calc_n.Common;
import org.dnf_calc_n.calculate.Buff;
import org.dnf_calc_n.calculate.Damage;
import org.dnf_calc_n.data.LoadImage;
import org.dnf_calc_n.data.LoadJob;
import org.dnf_calc_n.data.LoadString;
import org.dnf_calc_n.ui.component.RoundButton;
import org.dnf_calc_n.ui.sub.WindowCustomOption;
import org.dnf_calc_n.ui.sub.WindowExplain;
import org.dnf_calc_n.ui.sub.WindowSave;
import org.dnf_calc_n.ui.sub.WindowUpdate;
import org.dnf_calc_n.ui.sub.ItemFusion;
import org.json.simple.JSONObject;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.HashMap;

public class WindowMainNew extends JFrame {

    Common common = new Common();
    Buff buff;
    Damage damage;
    HashMap<String, Font> mapFont;
    JSONObject equipmentData;
    JSONObject customData;

    JPanel mainPanel;
    PanelInfo panelInfo;
    PanelSelect panelSelect;
    PanelCustom panelCustom;
    PanelResult panelResult;
    PanelCondition panelCondition;
    WindowUpdate windowUpdate;
    WindowExplain windowExplain;
    WindowCustomOption windowCustomOption;

    JSONObject jsonCache;

    HashMap<String, JButton> mapWidgetBtn = new HashMap<>();
    HashMap<String, JComboBox<String>> mapWidgetCombo = new HashMap<>();
    HashMap<String, JTextField> mapWidgetField = new HashMap<>();

    HashMap<String, ImageIcon> mapIconItem;
    HashMap<String, ImageIcon> mapIconExtra;
    HashMap<String, Boolean> mapToggleItem = new HashMap<>();

    public static void main(String[] args) {
        // 시스템 텍스트 인코딩 지정
        System.setProperty("file.encoding","UTF-8");
        try{
            Field charset = Charset.class.getDeclaredField("defaultCharset");
            charset.setAccessible(true);
            charset.set(null,null);
        }
        catch(Exception ignored){}
        LoadString loadString = new LoadString();
        EventQueue.invokeLater(() -> {
            try {
                WindowMainNew frame = new WindowMainNew();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("오류");
            }
        });
    }

    public WindowMainNew() {
        loadNowVersion();
        // 초기 데이터 로드
        LoadImage loadImage = new LoadImage();
        jsonCache = common.loadJsonObject("cache/selected.json");
        equipmentData = common.loadJsonObject("resources/data/equipment_data.json");
        customData = common.loadJsonObject("resources/data/custom_data.json");
        buff = new Buff(equipmentData, customData);
        damage = new Damage(equipmentData, customData);
        mapIconItem = loadImage.loadAllImageItem();
        mapIconExtra = loadImage.loadAllImageExtra();
        mapFont = common.loadFont();
        setResizable(false);
        setTitle(LoadString.strGet("에픽조합계산기N") + " "+nowVersion);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);
        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(34, 32, 37));
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(mainPanel);
        mainPanel.setLayout(null);
        JSONObject jsonSave = common.loadJsonObject("cache/saved.json");
        SwingUtilities.invokeLater(() -> {
            windowUpdate = new WindowUpdate(nowVersion, this);
            boolean isLatest = windowUpdate.isClientLatest();
                    if(!"1".equals(jsonSave.get("patchNoShow")) || !isLatest) {
                        windowUpdate.setVisible(true);
                    }
        });

        windowExplain = new WindowExplain(equipmentData, mapIconItem, mainPanel);

        // 영역 생성
        panelResult = new PanelResult(mainPanel);
        panelCondition = new PanelCondition(mainPanel, damage, buff, panelResult);
        panelInfo = new PanelInfo(
                mainPanel, mapIconItem, mapIconExtra, equipmentData,
                windowExplain);
        panelCustom = new PanelCustom(mainPanel, panelResult, panelInfo, panelCondition,
                mapWidgetCombo, buff, damage);
        windowCustomOption = new WindowCustomOption(
                customData, mainPanel, panelSelect, panelInfo, mapIconExtra,
                panelResult, buff, damage, panelCondition
        );
        panelSelect = new PanelSelect(
                mainPanel, panelResult, panelCondition,
                equipmentData, mapIconItem, panelInfo,
                buff, damage, mapWidgetCombo, windowExplain, windowCustomOption
        );

        JButton fusion = new JButton();
        fusion.setText(
                LoadString.strGet("융합픽선택")
        );
        fusion.setBounds(931+240, 489-479, 67, 56);
        fusion.setBackground(Color.LIGHT_GRAY);
        fusion.setBorder(new BevelBorder(BevelBorder.RAISED));
        fusion.setFont(mapFont.get("normal_bold"));
        fusion.setHorizontalAlignment(JButton.CENTER);
        fusion.setForeground(Color.BLACK);
        fusion.addActionListener(e -> {
            try {
                windowUpdate.dispose();
            }catch (Exception ignored){}
            windowUpdate = new WindowUpdate(nowVersion, this);
            windowUpdate.setVisible(true);
        });
        mainPanel.add(fusion);

        JButton update = new JButton();
        update.setText(
                "<html><body style='text-align:center;'>"+nowVersion+"<br>"+LoadString.strGet("버전확인") +"</body></html>"
        );
        update.setBounds(930+240, 560-479, 67, 56);
        update.setBackground(Color.LIGHT_GRAY);
        update.setBorder(new BevelBorder(BevelBorder.RAISED));
        update.setFont(mapFont.get("normal_bold"));
        update.setHorizontalAlignment(JButton.CENTER);
        update.setForeground(Color.BLACK);
        update.addActionListener(e -> {
            try{
                windowUpdate.dispose();
            }catch (Exception ignored){}
            windowUpdate = new WindowUpdate(nowVersion, this);
            windowUpdate.setVisible(true);
        });
        mainPanel.add(update);

        /*
        JLabel maker = new JLabel("<html><body style='text-align:center;'>Made By<br>Dawnclass<br>(새벽반)</body></html>");
        maker.setForeground(Color.WHITE);
        maker.setBounds(931+240, 620, 67, 50);
        maker.setHorizontalAlignment(JLabel.CENTER);
        maker.setFont(mapFont.get("small"));
        mainPanel.add(maker);
         */

    }

    String nowVersion = "1.0.0";
    private void loadNowVersion(){
        try {
            FileInputStream is = new FileInputStream("update.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            nowVersion = br.readLine();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(nowVersion);
    }


}
