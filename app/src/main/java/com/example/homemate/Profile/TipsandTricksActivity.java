package com.example.homemate.Profile;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;

import com.example.homemate.R;

public class TipsandTricksActivity extends AppCompatActivity {
    private ExpandableListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;
    private List<String> categories;
    private HashMap<String, List<String>> tipsAndTricks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipsand_tricks);

        expandableListView = findViewById(R.id.expandable_list_view);

        // Initialize data
        initData();

        // Initialize adapter
        expandableListAdapter = new TipsAndTricksAdapter(this, categories, tipsAndTricks);

        // Set adapter to ExpandableListView
        expandableListView.setAdapter(expandableListAdapter);

        // Expand the first group by default
        expandableListView.expandGroup(0);

        // Set click listener for child items
        expandableListView.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            String item = (String) expandableListAdapter.getChild(groupPosition, childPosition);
            Toast.makeText(getApplicationContext(), item, Toast.LENGTH_SHORT).show();
            return false;
        });
    }

    // Initialize data for ExpandableListView
    private void initData() {
        categories = new ArrayList<>();
        tipsAndTricks = new HashMap<>();

        // Add categories
        categories.add("General Maintenance Tips");
        categories.add("DIY Fixes and Troubleshooting");
        categories.add("Using HomeMate Effectively");
        categories.add("Safety Tips");
        categories.add("Cleaning Tips");
        categories.add("Energy Efficiency");
        categories.add("Home Security");
        categories.add("Outdoor Maintenance");
        categories.add("Indoor Air Quality");
        categories.add("Emergency Preparedness");
        categories.add("Appliance Maintenance");
        categories.add("Home Organization");


        // Add tips and tricks for each category
        List<String> maintenanceTips = new ArrayList<>();
        maintenanceTips.add("Regularly check for leaks under sinks and around toilets to prevent water damage.");
        maintenanceTips.add("Inspect electrical outlets and switches for signs of wear or damage.");
        maintenanceTips.add("Clean air vents and replace HVAC filters regularly to improve indoor air quality.");

        List<String> diyFixes = new ArrayList<>();
        diyFixes.add("Learn basic troubleshooting techniques for common household problems.");
        diyFixes.add("Use HomeMate's chat feature to ask service providers for advice on minor repairs.");

        List<String> usingHomeMate = new ArrayList<>();
        usingHomeMate.add("Take advantage of HomeMate's search feature to quickly find service providers.");
        usingHomeMate.add("Save time by setting up recurring appointments for routine maintenance tasks.");

        List<String> safetyTips = new ArrayList<>();
        safetyTips.add("Always verify the credentials and background of service providers.");
        safetyTips.add("Use HomeMate's real-time tracking feature to monitor the location of service providers.");

        List<String> cleaningTips = new ArrayList<>();
        cleaningTips.add("Use vinegar and baking soda to clean and deodorize drains and garbage disposals.");
        cleaningTips.add("Regularly vacuum and mop floors to remove dirt and allergens from your home.");
        cleaningTips.add("Wipe down kitchen counters and appliances daily to prevent bacterial growth.");
        cleaningTips.add("Clean and disinfect frequently touched surfaces, such as doorknobs and light switches.");
        cleaningTips.add("Rotate and launder bedding weekly to keep mattresses and pillows clean and fresh.");
        cleaningTips.add("Schedule professional carpet cleaning every 12-18 months to remove deep-seated dirt and stains.");

        List<String> energyEfficiency = new ArrayList<>();
        energyEfficiency.add("Install LED light bulbs to reduce energy consumption and lower electricity bills.");
        energyEfficiency.add("Seal drafts around windows and doors with weatherstripping to prevent heat loss in winter.");
        energyEfficiency.add("Use programmable thermostats to regulate indoor temperatures and save energy.");
        energyEfficiency.add("Upgrade to ENERGY STAR-rated appliances for improved energy efficiency.");
        energyEfficiency.add("Plant trees or install awnings to shade windows and reduce cooling costs in summer.");
        energyEfficiency.add("Insulate hot water pipes to minimize heat loss and conserve energy.");

        List<String> homeSecurity = new ArrayList<>();
        homeSecurity.add("Install motion-sensor lights around the perimeter of your home to deter intruders.");
        homeSecurity.add("Invest in a smart doorbell camera to monitor visitors and package deliveries.");
        homeSecurity.add("Secure sliding glass doors with auxiliary locks or security bars to prevent forced entry.");
        homeSecurity.add("Trim bushes and shrubs near windows and doors to eliminate hiding spots for intruders.");
        homeSecurity.add("Consider installing a home security system with monitoring and remote access features.");
        homeSecurity.add("Place decals or signs indicating the presence of a security system to deter potential burglars.");

        List<String> outdoorMaintenance = new ArrayList<>();
        outdoorMaintenance.add("Mow lawns regularly and trim overgrown vegetation to maintain curb appeal.");
        outdoorMaintenance.add("Inspect and clean gutters and downspouts to prevent water damage to your home's foundation.");
        outdoorMaintenance.add("Check outdoor faucets and hoses for leaks or damage and make repairs as needed.");
        outdoorMaintenance.add("Inspect and repair fences, gates, and outdoor structures for stability and safety.");
        outdoorMaintenance.add("Prune trees and shrubs away from the house to prevent damage from falling branches.");
        outdoorMaintenance.add("Apply a fresh coat of paint or sealant to decks, patios, and fences to protect them from the elements.");

        List<String> indoorAirQuality = new ArrayList<>();
        indoorAirQuality.add("Open windows regularly to ventilate your home and improve indoor air circulation.");
        indoorAirQuality.add("Use indoor plants to help filter indoor air pollutants and improve air quality.");
        indoorAirQuality.add("Invest in an air purifier with HEPA filters to remove airborne particles and allergens.");
        indoorAirQuality.add("Avoid smoking indoors and use exhaust fans when cooking to reduce indoor air pollution.");
        indoorAirQuality.add("Keep humidity levels between 30-50% to prevent mold and mildew growth.");

        List<String> emergencyPreparedness = new ArrayList<>();
        emergencyPreparedness.add("Create an emergency kit with essentials such as food, water, flashlights, and first aid supplies.");
        emergencyPreparedness.add("Develop a family emergency plan with evacuation routes and designated meeting spots.");
        emergencyPreparedness.add("Install smoke detectors on every level of your home and test them monthly.");
        emergencyPreparedness.add("Teach family members how to safely shut off utilities such as gas, water, and electricity.");
        emergencyPreparedness.add("Stay informed about local emergency alerts and weather forecasts to prepare for potential disasters.");

        List<String> applianceMaintenance = new ArrayList<>();
        applianceMaintenance.add("Clean refrigerator coils annually to improve energy efficiency and extend the lifespan of your fridge.");
        applianceMaintenance.add("Empty and clean the lint trap in your dryer after every use to prevent fire hazards and improve drying efficiency.");
        applianceMaintenance.add("Run dishwasher cleaner through your dishwasher regularly to remove built-up grease and mineral deposits.");
        applianceMaintenance.add("Inspect washing machine hoses for signs of wear or damage and replace them every 3-5 years.");
        applianceMaintenance.add("Schedule professional maintenance for HVAC systems and water heaters annually to keep them running smoothly.");

        List<String> homeOrganization = new ArrayList<>();
        homeOrganization.add("Declutter regularly to reduce stress and create a more organized living space.");
        homeOrganization.add("Use storage solutions such as baskets, bins, and shelving to maximize space and reduce clutter.");
        homeOrganization.add("Designate a specific spot for commonly misplaced items, such as keys, wallets, and sunglasses.");
        homeOrganization.add("Establish daily routines for tidying up and cleaning to maintain a clutter-free home.");
        homeOrganization.add("Invest in furniture with built-in storage, such as ottomans with hidden compartments or beds with under-bed drawers.");

        tipsAndTricks.put(categories.get(0), maintenanceTips);
        tipsAndTricks.put(categories.get(1), diyFixes);
        tipsAndTricks.put(categories.get(2), usingHomeMate);
        tipsAndTricks.put(categories.get(3), safetyTips);
        tipsAndTricks.put(categories.get(4), cleaningTips);
        tipsAndTricks.put(categories.get(5), energyEfficiency);
        tipsAndTricks.put(categories.get(6), homeSecurity);
        tipsAndTricks.put(categories.get(7), outdoorMaintenance);
        tipsAndTricks.put(categories.get(8), indoorAirQuality);
        tipsAndTricks.put(categories.get(9), emergencyPreparedness);
        tipsAndTricks.put(categories.get(10), applianceMaintenance);
        tipsAndTricks.put(categories.get(11), homeOrganization);


    }
}