//
//  MainViewController.swift
//  Me
//
//  Created by Jamal on 27/02/2018.
//  Copyright © 2018 Forus. All rights reserved.
//

import UIKit

var mainVC = MainViewController()
var uriMessage = ""

class MainViewController: UIViewController {
    
    @IBOutlet weak var identityHeight: NSLayoutConstraint!
    @IBOutlet weak var identityCenterY: NSLayoutConstraint!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        mainVC = self
    }
    
    @objc func showMessage() {
        delay(0.25) {
            if uriMessage != "" {
                let alert = UIAlertController(title: "", message: "\(uriMessage)", preferredStyle: .alert)
                alert.addAction(UIAlertAction(title: "OK", style: .cancel, handler: nil))
                
                self.present(alert, animated: true)
                
                uriMessage = ""
            }
        }
    }

    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        
        NotificationCenter.default.addObserver(self, selector:#selector(showMessage), name: NSNotification.Name.UIApplicationWillEnterForeground, object: nil)
        
        identityVC.filterView.alpha = 0.0
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
}
