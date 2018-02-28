//
//  MainViewController.swift
//  Me
//
//  Created by Jamal on 27/02/2018.
//  Copyright © 2018 Forus. All rights reserved.
//

import UIKit

var mainViewController: MainViewController?
var uriMessage = ""

class MainViewController: UIViewController {
    
    @IBOutlet weak var identityView: IdentityView!
    @IBOutlet weak var qrView: UIImageView!
    
    func generateQRCode(from string: String) -> UIImage? {
        let data = string.data(using: String.Encoding.ascii)
        
        if let filter = CIFilter(name: "CIQRCodeGenerator") {
            filter.setValue(data, forKey: "inputMessage")
            let transform = CGAffineTransform(scaleX: 50, y: 50)
            
            if let output = filter.outputImage?.transformed(by: transform) {
                return UIImage(ciImage: output)
            }
        }
        
        return nil
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()

        mainViewController = self
        
        let tokenQR = self.generateQRCode(from: "hello")
        self.qrView.image = tokenQR
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
        
        print("hey")
        
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
}
