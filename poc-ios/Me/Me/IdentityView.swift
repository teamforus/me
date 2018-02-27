//
//  IdentityView.swift
//  Me
//
//  Created by Jamal on 27/02/2018.
//  Copyright © 2018 Forus. All rights reserved.
//

import UIKit

class IdentityView: UIView {
    
    var vc: MainViewController?
    
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        if let vc = mainViewController {
            vc.performSegue(withIdentifier: "openScanner", sender: self)
        }
    }
}
