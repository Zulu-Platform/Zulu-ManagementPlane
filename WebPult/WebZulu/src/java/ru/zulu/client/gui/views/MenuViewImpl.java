/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.gui.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.Style;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.tree.Tree;
import ru.zulu.client.gui.stocks.ITreeData;
import ru.zulu.client.gui.stocks.TreeData;
import ru.zulu.client.gui.types.TypeMenu;

/**
 * Панель настройки правил.
 * @author Носов А.В.
 */
public class MenuViewImpl extends Composite implements MenuView {

    // Variables declaration
    private final String CLASS_NAME = this.getClass().getName();
    private IMenuPresenter presenter;

    /** Модель дерева меню. */
    private TreeStore<TreeData> treeModelMenu;
    /** Дерево меню. */
    private Tree<TreeData, String> treeMenu;
    // End of variables declaration

    /**
     * Создает новую панель.
     */
    public MenuViewImpl() {
        initComponents();
    }

    private void initComponents() {
        final ITreeData dp = GWT.create(ITreeData.class);
        treeModelMenu = new TreeStore<TreeData>(dp.key());
        treeMenu = new Tree<TreeData, String>(treeModelMenu, dp.name());


        creatTreeMenu();
        treeMenu.getSelectionModel().setSelectionMode(Style.SelectionMode.SINGLE);
        treeMenu.getSelectionModel().addSelectionHandler(new SelectionHandler<TreeData>() {
            @Override
            public void onSelection(SelectionEvent<TreeData> event) {
                TreeData td = event.getSelectedItem();
                presenter.goTo(td.getTypeMenu());
            }
        });

        initWidget(treeMenu);
    }

    @Override
    public Widget asWidget() {
        return this;
    }

    @Override
    public void setPresenter(IMenuPresenter presenter) {
        this.presenter = presenter;
    }

    /**
     * Создает меню дерево.
     */
    private void creatTreeMenu() {
        treeModelMenu.clear();

        TreeData monitoring = new TreeData(TypeMenu.Monitoring, true);
        treeModelMenu.add(monitoring);
            treeModelMenu.add(monitoring, new TreeData(TypeMenu.MonitoringStatusPorts, true));
            treeModelMenu.add(monitoring, new TreeData(TypeMenu.MonitoringStatisticsPorts, true));
            treeModelMenu.add(monitoring, new TreeData(TypeMenu.MonitoringUsePorts, true));
            treeModelMenu.add(monitoring, new TreeData(TypeMenu.MonitoringSwitchHealth, true));
            treeModelMenu.add(monitoring, new TreeData(TypeMenu.MonitoringLogs, true));
        TreeData systems = new TreeData(TypeMenu.Systems, true);
        treeModelMenu.add(systems);
            treeModelMenu.add(systems, new TreeData(TypeMenu.SystemsSettings, true));
            treeModelMenu.add(systems, new TreeData(TypeMenu.SystemsInstallation, true));
            treeModelMenu.add(systems, new TreeData(TypeMenu.SystemsLogs, true));
            treeModelMenu.add(systems, new TreeData(TypeMenu.SystemsSNTP, true));
        TreeData interf = new TreeData(TypeMenu.Interface, true);
        treeModelMenu.add(interf);
            treeModelMenu.add(interf, new TreeData(TypeMenu.InterfaceEthernet, true));
            treeModelMenu.add(interf, new TreeData(TypeMenu.InterfaceTCP, true));
            treeModelMenu.add(interf, new TreeData(TypeMenu.InterfaceIPv4, true));
            treeModelMenu.add(interf, new TreeData(TypeMenu.InterfaceToS, true));
            treeModelMenu.add(interf, new TreeData(TypeMenu.InterfaceMPLS, true));
            treeModelMenu.add(interf, new TreeData(TypeMenu.InterfaceBGP4, true));
            treeModelMenu.add(interf, new TreeData(TypeMenu.InterfaceVLAN, true));
        treeModelMenu.add(new TreeData(TypeMenu.Mirroring, true));
        TreeData firewall = new TreeData(TypeMenu.Firewall, true);
        treeModelMenu.add(firewall);
            treeModelMenu.add(firewall, new TreeData(TypeMenu.FirewallICMP, true));
        treeModelMenu.add(new TreeData(TypeMenu.NAT, true));
        TreeData security = new TreeData(TypeMenu.Security, true);
        treeModelMenu.add(security);
            treeModelMenu.add(security, new TreeData(TypeMenu.SecurityTrustedHost, true));
            treeModelMenu.add(security, new TreeData(TypeMenu.SecurityPorts, true));
            treeModelMenu.add(security, new TreeData(TypeMenu.SecurityTelnet, true));
            treeModelMenu.add(security, new TreeData(TypeMenu.SecurityARP, true));
            treeModelMenu.add(security, new TreeData(TypeMenu.SecurityMAC, true));
            treeModelMenu.add(security, new TreeData(TypeMenu.SecurityIP, true));
//        TreeData userGroup = new TreeData(TypeMenu.UserGroup, true);
//        treeModelMenu.add(userGroup);
//            treeModelMenu.add(userGroup, new TreeData(TypeMenu.Users, true));
//            treeModelMenu.add(userGroup, new TreeData(TypeMenu.Groups, true));
        treeModelMenu.add(new TreeData(TypeMenu.Users, true));
        TreeData tools = new TreeData(TypeMenu.Tools, true);
        treeModelMenu.add(tools);
            treeModelMenu.add(tools, new TreeData(TypeMenu.ToolsReboot, true));
            treeModelMenu.add(tools, new TreeData(TypeMenu.ToolsReset, true));
            treeModelMenu.add(tools, new TreeData(TypeMenu.ToolsBackup, true));
            treeModelMenu.add(tools, new TreeData(TypeMenu.ToolsUpdate, true));
        treeModelMenu.add(new TreeData(TypeMenu.Help, true));
    }
}
