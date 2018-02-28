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
    
    var identityExpanded = false
    
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
    
    func expandIdentity() {
        UIView.animate(withDuration: 0.2) {
            if self.identityExpanded {
                identityVC.reader.previewLayer.frame = CGRect(x: 0.0, y: 0.0, width: 75, height: 75)
                identityVC.filterView.alpha = 0.0
                
                self.identityHeight.constant = 75
                self.identityCenterY.constant = 244
                self.identityExpanded = false
            } else {
                identityVC.reader.previewLayer.frame = CGRect(x: 0.0, y: 0.0, width: 200, height: 200)
                identityVC.filterView.alpha = 0.4
                
                self.identityHeight.constant = 200
                self.identityCenterY.constant = 0
                self.identityExpanded = true
            }
            
            self.view.layoutIfNeeded()
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
