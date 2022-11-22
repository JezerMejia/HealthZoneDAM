package registrer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jezerm.healthzone.databinding.FragmentHomePatientBinding
import com.jezerm.healthzone.databinding.FragmentRegistrerPatientBinding

class RegistrarPatientFragment : Fragment() {
    private lateinit var binding: FragmentRegistrerPatientBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            //param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrerPatientBinding.inflate(inflater, container, false)
        return binding.root
    }
}